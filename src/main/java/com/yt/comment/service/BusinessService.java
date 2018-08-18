package com.yt.comment.service;

import com.yt.comment.dto.BusinessDto;
import com.yt.comment.dto.BusinessListDto;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */

public interface BusinessService {

    /**
     * 新增
     * @param businessDto 商户dto对象
     * @return
     */
    boolean add(BusinessDto businessDto);

    /**
     * 根据主键获取商户dto
     * @param id
     * @return
     */
    BusinessDto getById(Long id);

    /**
     * 分页搜索商户列表
     * @param businessDto 查询条件
     * @return
     */
    List<BusinessDto> searchByPage(BusinessDto businessDto);

    /**
     * 分页搜索用户列表(接口专用)
     * @param businessDto 查询条件（包含分页对象）
     * @return
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 修改商户
     * @param businessDto
     * @return
     */
    boolean modify(BusinessDto businessDto);
}
