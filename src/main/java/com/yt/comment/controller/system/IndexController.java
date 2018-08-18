package com.yt.comment.controller.system;

import com.yt.comment.constant.PageCodeEnum;
import com.yt.comment.dto.PageCodeDto;
import com.yt.comment.dto.UserDtoForReset;
import com.yt.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 登录成功后，后台管理首页
     * @return
     */
    @RequestMapping
    public String init() {
        return "system/index";
    }

    @RequestMapping(value = "/resetPwd",method = RequestMethod.POST)
    @ResponseBody
    public PageCodeDto resetPassword(UserDtoForReset dto) {
        PageCodeDto result = null;
        //验证原始密码
        if (userService.checkPwd(dto)) {
            //修改密码
            if (userService.resetPwd(dto)) {
                result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
            } else {
                result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
            }
        } else {
            result = new PageCodeDto(PageCodeEnum.VALIDATE_PWD_FAIL);
        }
        return result;
    }
}
