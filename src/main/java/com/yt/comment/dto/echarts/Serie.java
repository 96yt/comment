package com.yt.comment.dto.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:与前端的series相对应
 *
 * @author:Tong
 */
public class Serie {

    private String name;
    private String type;
    private List<Long> data;

    public static final String TYPE_NAME = "line";

    public Serie() {
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
