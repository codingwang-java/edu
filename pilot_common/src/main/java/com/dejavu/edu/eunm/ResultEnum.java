package com.dejavu.edu.eunm;

/**
 * @author dejavu
 * @description
 * @create 2021-02-27 02:17
 **/
public enum ResultEnum {
    UNAUTHORIZED(20001,"无访问权限"),
    ;
    private int code;
    private String msg;
    ResultEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
