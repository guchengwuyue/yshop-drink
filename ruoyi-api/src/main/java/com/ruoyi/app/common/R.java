package com.ruoyi.app.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class R<M> {
    // 返回状态【0-成功，1-业务失败，999-表示系统异常】
    @JSONField(ordinal = 1)
    private int code;
    // 返回信息
    @JSONField(ordinal = 2)
    private String message;
    // 返回数据实体;
    @JSONField(ordinal = 3)
    private M data;


    private R(){}


    public static<M> R success(M m){
        R r = new R();
        r.setCode(0);
        r.setMessage("success");
        r.setData(m);

        return r;
    }

    public static<M> R success(String msg){
        R r = new R();
        r.setCode(0);
        r.setMessage(msg);

        return r;
    }

    public static<M> R serviceFail(String msg){
        R r = new R();
        r.setCode(1);
        r.setMessage(msg);

        return r;
    }

    public static<M> R appFail(String msg){
        R r = new R();
        r.setCode(999);
        r.setMessage(msg);

        return r;
    }

    public static<M> R error(int code,String msg){
        R r = new R();
        r.setCode(code);
        r.setMessage(msg);

        return r;
    }

}
