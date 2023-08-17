package com.xzjie.cms.exception.handler;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.enums.Business;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class WebGlobalExceptionHandler {

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> error(MissingServletRequestParameterException e) {
        log.error("请求参数", e);
        return Result.fail("请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error("参数体", e);
        return Result.fail("参数体不能为空");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Map<String, Object>> handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}，异常类型{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, Object> errorMap = new HashMap<>();

        bindingResult.getFieldErrors().forEach(item -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        return Result.fail(Business.BAD_PARAM.getCode(), "错误的参数", errorMap);
    }

//    /**
//     * 自定义参数错误异常处理器
//     *
//     * @param e 自定义参数
//     * @return ResponseInfo
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({ParamaErrorException.class})
//    public  Map<String, Object> paramExceptionHandler(ParamaErrorException e) {
//        logger.error("", e);
//        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
//        if (!StringUtils.isEmpty(e.getMessage())) {
//            return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(), e.getMessage());
//        }
//        return new ResponseResult(ResultEnum.PARAMETER_ERROR);
//    }

    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public Result<Object> otherExceptionHandler(Exception e) {
        log.error("其他异常", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return Result.fail(e.getMessage());
        }
        return Result.fail("未知的错误!");
    }


}
