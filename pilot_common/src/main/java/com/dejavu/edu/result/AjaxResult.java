package com.dejavu.edu.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dejavu
 * @description
 * @create 2021-02-27 02:04
 **/
@Data
@Accessors(chain = true)
public class AjaxResult {
    private int code;
    private String msg;
    private Object object;
}
