package com.yt.comment.controller.system;

import com.yt.comment.constant.PageCodeEnum;
import com.yt.comment.constant.SessionKeyConst;
import com.yt.comment.dto.GroupDto;
import com.yt.comment.dto.UserDto;
import com.yt.comment.service.GroupService;
import com.yt.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Description:登录相关
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private HttpSession session;

    /**
     * 登录页面（后台入口）
     * @return
     */
    @RequestMapping
     public String index() {
         return "/system/login";
     }

    /**
     * 验证用户名／密码是否正确 验证通过跳转至后台管理首页，验证失败，返回至登录页
     * @param userDto
     * @param attr
     * @return
     */
     @RequestMapping("/validate")
     public String validate(UserDto userDto, RedirectAttributes attr) {
        if (userService.validate(userDto)) {
            session.setAttribute(SessionKeyConst.USER_INFO,userDto);
            GroupDto groupDto = groupService.getByIdWithMenuAction(userDto.getGroupId());
            session.setAttribute(SessionKeyConst.MENU_INFO,groupDto.getMenuDtoList());
            session.setAttribute(SessionKeyConst.ACTION_INFO,groupDto.getActionDtoList());
            return "redirect:/index";
        }
        attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.LOGIN_FAIL);
        return "redirect:/login";
     }

    /**
     * session超时（只要session不存在都是视为session超时）
     * @param model
     * @return
     */
     @RequestMapping("/sessionTimeOut")
     public String sessionTimeOut(Model model) {
         model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.SESSION_TIMEOUT);
         return "/system/error";
     }

    /**
     * 没有权限访问
     * @param model
     * @return
     */
     @RequestMapping("/noAuth")
     public String noAuth(Model model) {
         model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.NO_AUTH);
         session.invalidate();
         return "/system/error";
     }
}
