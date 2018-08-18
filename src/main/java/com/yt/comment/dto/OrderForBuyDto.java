package com.yt.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Description:
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForBuyDto {

    private Long id;//商户主键
    private String token;//登录成功后的token
    private Integer num;//消费人数前端默认为1
    private Double price;//消费金额
    private Long username;//会员手机号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }
}
