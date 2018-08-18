package com.yt.comment.controller.system;

import com.yt.comment.constant.PageCodeEnum;
import com.yt.comment.dto.PageCodeDto;
import com.yt.comment.dto.UserDto;
import com.yt.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:用户相关
 *
 * @author:Tong
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param userDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(UserDto userDto) {
        PageCodeDto result;
        if (userService.add(userDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto remove(@PathVariable("id") Long id) {

        PageCodeDto result;
        if (userService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public PageCodeDto modify(UserDto userDto) {
        PageCodeDto result;
        if (userService.modify(userDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        }else {
            result = new PageCodeDto(PageCodeEnum.USERNAME_EXISTS);
        }
        return result;
    }

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getList() {
        return userService.getList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}