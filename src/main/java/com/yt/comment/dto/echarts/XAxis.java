package com.yt.comment.dto.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:与前端的xAxis相对应
 *
 * @author:Tong
 */
public class XAxis {

    private List<String> data;

    public XAxis() {
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
