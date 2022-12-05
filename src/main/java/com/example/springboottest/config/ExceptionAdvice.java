package com.example.springboottest.config;

import com.example.springboottest.util.ResponseResult;
import com.example.springboottest.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 全局异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(Exception ex){
        log.error("系统异常：", ex);
        return ResponseResult.error();
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult exception(Throwable throwable) {
        log.error("系统异常", throwable);
        return ResponseResult.error();
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String message = objectError.getDefaultMessage();
        if (objectError instanceof FieldError) {
            message = ((FieldError) objectError).getField() + ": " + message;
        }
        return ResponseResult.errorMessage(message);
    }
}
