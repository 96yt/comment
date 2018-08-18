package com.yt.comment.dto.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:与前端的legend相对应
 *
 * @author:Tong
 */
public class Legend {

    private List<String> data;

    public Legend() {
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
