package com.yt.comment.mapper;

import com.yt.comment.entity.User;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface UserMapper {

    /**
     * 新增
     * @param user
     * @return 影响行数：0：用户名存在 1：新增成功
     */
    int insert(User user);

    /**
     * 根据主键删除
     * @param id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 修改
     * @param user
     * @return 影响行数 0：用户名存在无法修改 1：修改成功
     */
    int update(User user);

    /**
     * 根据查询条件查询用户列表
     * @param user
     * @return 用户列表
     */
    List<User> select(User user);

    /**
     * 根据主键获取用户实体
     * @param id
     * @return 用户实体
     */
    User selectById(Long id);

}
