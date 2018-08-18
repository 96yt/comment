package com.yt.comment.dto;

import com.yt.comment.entity.User;
import sun.security.util.Password;

/**
 * Description:
 *
 * @author:Tong
 */
public class UserDto extends User {

    //zTree中父亲节点的id
    private Integer pId;
    private String oldPassword;
    private String newPassword;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
