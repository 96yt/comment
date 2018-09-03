package com.yt.comment.controller.content;

import com.yt.comment.constant.PageCodeEnum;
import com.yt.comment.dto.AdDto;
import com.yt.comment.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:广告控制层
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    /**
     * 广告页面初始化(点广告管理菜单进入第一个页面)
     * @return
     */
    @RequestMapping
    public String init(Model model, HttpServletRequest request,AdDto adDto) {
        model.addAttribute("list",adService.searchByPage(adDto));
        model.addAttribute("searchParam",adDto);
        return "/content/adList";
    }

    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("/addInit")
    public String addInit() {
        return "/content/adAdd";
    }

    /**
     * 查询
     * @param model
     * @param adDto
     * @return
     */
    @RequestMapping("/search")
    public String search(Model model,AdDto adDto) {//adDto会传入拦截器中然后setTotalNumber，然后就有page的信息了
        model.addAttribute("list",adService.searchByPage(adDto));//列表中的adDto中的page没什么作用
        model.addAttribute("searchParam",adDto);
        return "/content/adList";
    }

    /**
     * 新增
     * @param adDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(AdDto adDto, Model model) {

        if (adService.add(adDto)) {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public String remove(@RequestParam("id") Long id, Model model) {
        if (adService.remove(id)) {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad";
    }

    /**
     * 修改页面初始化
     */
    @RequestMapping("/modifyInit ")
    public String modifyInit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("modifyObj",adService.getById(id));
        return "/content/adModify";
    }

    /**
     * 修改
     */
    @RequestMapping("/modify")
    public String modify(Model model,AdDto adDto) {
        model.addAttribute("modifyObj",adDto);
        if (adService.modify(adDto)) {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/adModify";
    }
}
