package com.yt.comment.service;

import com.yt.comment.dto.UserDto;
import com.yt.comment.dto.UserDtoForReset;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface UserService {

    /**
     * 校验用户名／密码是否正确
     * @param userDto
     * @return
     */
    boolean validate(UserDto userDto);

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    boolean add(UserDto userDto);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<UserDto> getList();

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    UserDto getById(Long id);

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    boolean modify(UserDto userDto);

    /**
     * 检查用户名密码
     * @param dto
     * @return
     */
    boolean checkPwd(UserDtoForReset dto);

    /**
     * 重置密码
     * @param dto
     * @return
     */
    boolean resetPwd(UserDtoForReset dto);
}
