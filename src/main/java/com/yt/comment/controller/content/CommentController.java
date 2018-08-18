package com.yt.comment.controller.content;

import com.yt.comment.dto.CommentDto;
import com.yt.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author:Tong
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping
    public String search(CommentDto dto, Model model) {
        model.addAttribute("commentList",commentService.getListByCondition(dto));
        model.addAttribute("searchParam",dto);
        return "/content/commentList";
    }
}
