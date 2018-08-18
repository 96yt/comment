package com.yt.comment.controller.report;

import com.yt.comment.dto.echarts.Option;
import com.yt.comment.service.OrderReportService;
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
@RequestMapping("/orderReport")
public class OrderReportController {

    @Autowired
    private OrderReportService orderReportService;

    @RequestMapping
    public String index() {
        return "/report/orderCount";
    }

    @ResponseBody
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Option count() {
        return orderReportService.count();
    }
}
