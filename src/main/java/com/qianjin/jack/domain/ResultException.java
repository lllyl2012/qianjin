package com.qianjin.jack.domain;


import lombok.Data;

/**
 * code 自定义错误码 -1表示通用异常
 * message 自定义 错误信息
 * exceptionEnum 自定义错误枚举
 */
@Data
public class ResultException extends RuntimeException {

    private Integer code;

    private String message;

    public static final String UnSupportOperation = "不支持该操作";


    public static ResultException ex(String message) {
        return new ResultException(message);
    }

    public ResultException(String message) {
        this.code = -1;
        this.message = message;
    }

    public ResultException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultException(exceptionEnum exceptionEnum) {
        this.code = exceptionEnum.code;
        this.message = exceptionEnum.message;
    }


    public enum exceptionEnum {


        LoginError(210, "账号或密码错误"),
        UnResult(220, "无查询结果");

        private int code;

        private String message;

        exceptionEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
