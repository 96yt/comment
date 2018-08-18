package com.yt.comment.util;

import com.yt.comment.constant.SessionKeyConst;
import com.yt.comment.dto.ActionDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * Description:通用工具类
 *
 * @author:Tong
 */
public class CommonUtil {

    /**
     * 方法判断：判断一个字符串是否为null或者空字符串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 隐藏电话号码中间4位
     * @param originalPhone
     * @return
     */
    public static String hidePhone(String originalPhone) {
        StringBuffer phoneBuffer = new StringBuffer(originalPhone);
        return phoneBuffer.replace(3,7,"****").toString();
    }

    /**
     * 生成指定位数的随机整数
     * @param number
     * @return
     */
    public static int random(int number) {
        return (int) ((Math.random() * 9 + 1) * (Math.pow(10,number - 1)));
    }

    /**
     * 生成指定范围的随机整数
     * @param rangeL 最小
     * @param rangeR 最大
     * @return
     */
    public static int random(int rangeL,int rangeR) {
        if (rangeR >= rangeL) {
            return (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return 0;
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 判断session中存放的动作dto列表是否包含指定的url
     * @param session
     * @param url
     * @param method http动作
     * @return
     */
    public static boolean containsUrl(HttpSession session,String url,String method) {

        Object attribute  =session.getAttribute(SessionKeyConst.ACTION_INFO);
        if (attribute != null) {
            @SuppressWarnings("unckecked")
            List<ActionDto> actionDtoList = (List<ActionDto>) attribute;

            for (ActionDto actionDto : actionDtoList) {
                //判断方法类型
                if (!isEmpty(actionDto.getMethod()) && !actionDto.getMethod().equals(method)) {
                    continue;
                }
                //判断路径
                if (!url.matches(actionDto.getUrl())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
