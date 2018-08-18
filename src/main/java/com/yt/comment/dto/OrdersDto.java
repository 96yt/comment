package com.yt.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yt.comment.entity.Orders;

/**
 * Description:
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersDto extends Orders{

    private String img;
    private String title;
    private Integer count;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
