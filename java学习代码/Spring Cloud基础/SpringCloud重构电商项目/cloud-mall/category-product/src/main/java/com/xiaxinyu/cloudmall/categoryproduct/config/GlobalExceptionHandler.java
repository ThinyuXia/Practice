package com.xiaxinyu.cloudmall.categoryproduct.config;

import com.xiaxinyu.cloudmall.common.common.ApiRestResponse;
import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理统一异常的handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("Default Exception : " + e);
        return ApiRestResponse.error(ExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(ExceptionUnify.class)
    @ResponseBody
    public Object handleException(ExceptionUnify e){
        log.error("Service Exception : " + e);
        return ApiRestResponse.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleMethodArgumentException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException : " + e);
        return handleBingResult(e.getBindingResult());
    }

    private ApiRestResponse handleBingResult(BindingResult result){
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for(ObjectError objectError : allErrors){
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if(list.size() == 0){
            return ApiRestResponse.error(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(ExceptionEnum.REQUEST_PARAM_ERROR.getCode(),list.toString());
    }
}
