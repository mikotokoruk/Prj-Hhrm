package com.example.authority.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: hhrm 返回数据封装类
 * @ClassName Result
 * @description:
 * @author: glb
 * @create: 2023-02-01 16:11
 * @Version 1.0
 **/
@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result success() {
        Result r = new Result();
        r.setCode(200);
        r.setMsg("操作成功");
        return r;
    }

    public static Result error() {
        return error(400, "操作失败", null);
    }

    public static Result error(String msg) {
        return error(400, msg, null);
    }

    public static Result error(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
