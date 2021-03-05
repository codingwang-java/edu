package com.dejavu.edu.utils;

import com.alibaba.fastjson.JSONObject;
import com.dejavu.edu.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dejavu
 * @description
 * @create 2021-02-25 00:02
 **/
@Slf4j
public class ResponseUtil {

    /**
     * 使用response输出JSON
     * @param response
     * @param ajaxResult
     */
    public static void out(HttpServletResponse response, AjaxResult ajaxResult) {

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getOutputStream().write(JSONObject.toJSONBytes(ajaxResult));
        } catch (Exception e) {
            log.error(e + "，输出JSON出错");
        }
    }
}
