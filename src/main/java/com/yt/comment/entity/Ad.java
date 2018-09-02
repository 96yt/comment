package com.yt.comment.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Description:
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad extends Base{

    private Long id;
    private String title;
    private String imgFileName;
    private String link;
    private Long weight;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}