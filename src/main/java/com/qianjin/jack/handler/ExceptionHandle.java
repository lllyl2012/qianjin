package com.qianjin.jack.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qianjin.jack.domain.Result;
import com.qianjin.jack.domain.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle extends DefaultHandlerExceptionResolver {

    @Value("${prod.test}")
    private String isTest;


    @ExceptionHandler(value = ResultException.class)
    public Object ResultExceptionHandler(ResultException e) {

        if (e.getCode() == 401 || e.getCode() == 999 || e.getCode() == 998 || e.getCode() == 997) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new Result(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public Result Except(Exception e) {

        if ("test".equals(isTest)) {
            e.printStackTrace();
            return new Result(-1, e.getMessage());
        }
        if (e instanceof ResultException) {
            ResultException re = (ResultException) e;
            if (re.getCode() == 730) {
                return new Result(e.getMessage(), -1, e.getMessage());
            }
        }
        e.printStackTrace();
        return new Result(e.getMessage(), -1, "系统错误");
    }


    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public Result uploadPhotoExp(MaxUploadSizeExceededException e) {
        return new Result(550, "图片太大了 请上传小一点的图片 不能大于10MB");
    }
}
