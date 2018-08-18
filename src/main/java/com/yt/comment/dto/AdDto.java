package com.yt.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yt.comment.entity.Ad;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:广告数据传输对象
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdDto extends Ad {

    private String img;

    private MultipartFile imgFile;

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
