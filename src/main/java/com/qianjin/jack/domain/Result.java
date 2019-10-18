package com.qianjin.jack.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class Result {
    private Object data;
    private String message;
    private Integer result;


    public Result(Object data) {
        this.result = 1;
        this.message = "请求成功";
        this.data = data;

    }

    public static Result of(Object o) {
        return new Result(o);
    }

    public static Result err(String message) {
        return new Result(-1, message);
    }

    public Result(List list) {
        if (list.isEmpty()) {
            this.result = 1;
            this.message = "请求成功";
            this.data = "暂无数据";
        } else {
            this.result = 1;
            this.message = "请求成功";
            this.data = list;
        }
    }

    public Result(Integer result, String message) {
        this.result = result;
        this.message = message;
        this.data = "";
    }

    public Result(Object o, Integer result, String message) {
        this.result = result;
        this.message = message;
        this.data = o;
    }

}
