package com.yt.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yt.comment.entity.Business;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business {

    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer mumber;
    private String subTitle;//兼容前端的subTitle
    private Integer star;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getMumber() {
        return mumber;
    }

    public void setMumber(Integer mumber) {
        this.mumber = mumber;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
