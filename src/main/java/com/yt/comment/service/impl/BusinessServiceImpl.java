package com.yt.comment.service.impl;

import com.yt.comment.constant.CategoryConst;
import com.yt.comment.dto.BusinessDto;
import com.yt.comment.dto.BusinessListDto;
import com.yt.comment.entity.Business;
import com.yt.comment.entity.Page;
import com.yt.comment.mapper.BusinessMapper;
import com.yt.comment.service.BusinessService;
import com.yt.comment.util.CommonUtil;
import com.yt.comment.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Value("${businessImage.savePath}")
    private String savePath;
    @Value("${businessImage.url}")
    private String url;

    @Override
    public boolean add(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() >0) {
            try {
                String fileName = FileUtil.save(businessDto.getImgFile(),savePath);
                business.setImgFileName(fileName);
                //默认为0
                business.setPrice(0D);
                business.setNumber(0);
                business.setCommentTotalNum(0L);
                business.setStarTotalNum(0L);
                businessMapper.insert(business);
                return true;
            } catch (IllegalStateException | IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public BusinessDto getById(Long id) {
        BusinessDto result = new BusinessDto();
        Business business = businessMapper.selectById(id);
        BeanUtils.copyProperties(business,result);
        result.setImg(url + business.getImgFileName());
        result.setStar(this.getStar(business));
        result.setPrice((double) this.getPrice(business));
        return result;
    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {

        List<BusinessDto> result = new ArrayList<>();
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto,businessForSelect);
        List<Business> list = businessMapper.selectByPage(businessForSelect);
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business,businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            businessDtoTemp.setStar(this.getStar(business));
            businessDtoTemp.setPrice((double)this.getPrice(business));
        }
        return result;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {

        BusinessListDto result = new BusinessListDto();
        Business businessForSelect = new Business();//组织查询条件
        BeanUtils.copyProperties(businessDto,businessForSelect);
        if (!CommonUtil.isEmpty(businessDto.getKeyword())) {//关键值不为空时，分别放入标题、副标题、描述中
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        //当类别为全部时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        //前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);
        List<Business> list = businessMapper.selectLikeByPage(businessForSelect);//从Dao层查出相关数据格式
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        //对查询出的结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business,businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            //为兼容前端mumber这个属性
            businessDtoTemp.setMumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
            businessDtoTemp.setPrice((double)this.getPrice(business));
            //为兼容前端subTitle这个属性
            businessDtoTemp.setSubTitle(business.getSubtitle());
        }
        return result;
    }

    @Override
    public boolean remove(Long id) {
        Business business = businessMapper.selectById(id);
        int deleteRow = businessMapper.delete(id);
        FileUtil.delete(savePath + business.getImgFileName());
        return deleteRow == 1;
    }

    @Override
    public boolean modify(BusinessDto businessDto) {

        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        //判断是否有新文件上传
        MultipartFile imgFile = businessDto.getImgFile();
        String fileName = null;
        if (imgFile != null && imgFile.getSize() > 0) {
            try {
                fileName = FileUtil.save(imgFile,savePath);
                business.setImgFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (businessMapper.update(business) != 1)
            return false;
        if (fileName != null)
            FileUtil.delete(savePath + businessDto.getImgFileName());

        return true;
    }

    private int getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null
                && business.getCommentTotalNum() != 0) {
            return (int) (business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }

    private int getPrice(Business business) {
        if (business.getPrice() != null && business.getNumber() != null && business.getNumber() != 0) {
            return (int) (business.getPrice() / business.getNumber());
        } else {
            return 0;
        }
    }
}
