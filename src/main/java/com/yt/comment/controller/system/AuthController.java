package com.yt.comment.controller.system;

import com.yt.comment.constant.DicTypeConst;
import com.yt.comment.entity.Dic;
import com.yt.comment.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:权限管理控制层
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DicService dicService;

    @RequestMapping
    public String index(Model model) {
        List<Dic> httpMethodList = dicService.getListByType(DicTypeConst.HTTP_METHOD);
        model.addAttribute("httpMethodList",httpMethodList);
        return "/system/auth";
    }
}
