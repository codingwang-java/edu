package com.dejavu.edu.utils;

import com.dejavu.edu.result.AjaxResult;

/**
 * @author dejavu
 * @description
 * @create 2021-02-27 02:08
 **/
public class ResultUtils {
    public static AjaxResult susscessResult(int code, String msg, Object obj){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code).setMsg(msg).setObject(obj);
        return ajaxResult;
    }
    public static AjaxResult failaureResult(int code, String msg){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code).setMsg(msg);
        return ajaxResult;
    }
}
