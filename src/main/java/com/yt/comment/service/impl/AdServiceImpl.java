package com.yt.comment.service.impl;

import com.yt.comment.dto.AdDto;
import com.yt.comment.entity.Ad;
import com.yt.comment.mapper.AdMapper;
import com.yt.comment.service.AdService;
import com.yt.comment.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Value("${adImage.savePath}")
    private String adImageSavePath;
    @Value("${adImage.url}")
    private String adImageUrl;

    @Override
    public boolean add(AdDto adDto) {
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            Ad ad = new Ad();
            File fileFolder = new File(adImageSavePath);
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }
//            构建该文件的新名字，上传、存库
            String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath + fileName);

            try {
                //上传文件
                adDto.getImgFile().transferTo(file);
                ad.setTitle(adDto.getTitle());
                ad.setImgFileName(fileName);
                ad.setLink(adDto.getLink());
                ad.setWeight(adDto.getWeight());
                adMapper.insert(ad);
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<AdDto> searchByPage(AdDto adDto) {

        List<AdDto> result = new ArrayList<>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto,condition);
        List<Ad> adList = adMapper.selectByPage(condition);
        for (Ad ad:
             adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
            //因为是继承所以难免有些属性不需要使用，可以置空就不会转化到json中
            adDtoTemp.setImgFile(null);
            adDtoTemp.setPage(null);
        }
        return result;
    }

    @Override
    public boolean remove(Long id) {
        Ad ad = adMapper.selectById(id);
        int deleteRows = adMapper.delete(id);
        FileUtil.delete(adImageSavePath + ad.getImgFileName());
        return deleteRows == 1;
    }

    @Override
    public AdDto getById(Long id) {
        AdDto result = new AdDto();
        Ad ad = adMapper.selectById(id);
        BeanUtils.copyProperties(ad,result);
        result.setImg(adImageUrl + ad.getImgFileName());
        return result;
    }

    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto,ad);
        String fileName = null;
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(),adImageSavePath);
                ad.setImgFileName(fileName);
            } catch (IllegalStateException | IOException e) {
                return false;
            }
        }
        int updateCount = adMapper.update(ad);
        if (updateCount != 1) {
            return false;
        }
        if (fileName != null) {
            return FileUtil.delete(adImageSavePath + adDto.getImgFileName());
        }
        adDto.setImg(adImageUrl + ad.getImgFileName());
        return true;
    }
}
