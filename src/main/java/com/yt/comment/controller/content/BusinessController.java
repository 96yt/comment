package com.yt.comment.controller.content;

import com.yt.comment.constant.DicTypeConst;
import com.yt.comment.constant.PageCodeEnum;
import com.yt.comment.dto.BusinessDto;
import com.yt.comment.service.BusinessService;
import com.yt.comment.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Description:
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private DicService dicService;

    /**
     * 商户列表
     * @param model
     * @param dto
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, BusinessDto dto) {

        model.addAttribute("list",businessService.searchByPage(dto));
        model.addAttribute("searchParam",dto);

        return "/content/businessList";
    }

    /**
     * 商户新增页初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPage",method = RequestMethod.GET)
    public String addInit(Model model) {
        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
        return "/content/businessAdd";
    }

    /**
     * 商户新增
     * @param dto
     * @param attr
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto dto, RedirectAttributes attr) {

        if (businessService.add(dto)) {
            attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
            return "redirect:/businesses";
        } else {
            attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
            return "redirect:/businesses/addPage";
        }
    }

    /**
     * 删除商户
     * @param id
     * @param arr
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String remove(@PathVariable("id") Long id,RedirectAttributes arr) {
        if (businessService.remove(id)) {
            arr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
        } else {
            arr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
        }
        return "redirect:/businesses";
    }

    /**
     * 商户修改页面初始化
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String modifyInit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("cityList",dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
        model.addAttribute("modifyObj",businessService.getById(id));
        return "/content/businessModify";
    }

    /**
     * 商户修改
     * @param dto
     * @param arr
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String modify(BusinessDto dto,RedirectAttributes arr) {
        if (businessService.modify(dto)) {
            arr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
        } else {
            arr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }
        return "redirect:/businesses/" + dto.getId();
    }
}
