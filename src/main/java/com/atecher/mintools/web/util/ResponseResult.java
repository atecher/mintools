package com.atecher.mintools.web.util;

import java.io.Serializable;

/**
 * Created by hanhongwei on 2016/6/22.
 */
public class ResponseResult implements Serializable {
    private String code;
    private Object result;

    public ResponseResult(String code, Object result){
        this.result=result;
        this.code=code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
