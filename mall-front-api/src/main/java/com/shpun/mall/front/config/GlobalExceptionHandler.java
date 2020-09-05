package com.shpun.mall.front.config;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.vo.MallResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 全局异常处理
 * @Author: sun
 * @Date: 2020/8/23 17:59
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 MallException
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MallException.class)
    public MallResultVo<?> mallExceptionHandler(MallException e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        Integer code = e.getCode() != null ? e.getCode() : Const.API_RETURN_CODE_INTERNAL_SERVER_ERROR;
        return MallResultVo.failure(code, e.getMessage());
    }

    /**
     * path，参数校验 抛出 ConstraintViolationException 异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public MallResultVo<?> validationPathException(ConstraintViolationException exception) {
        Set set = exception.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();

        String message = "";
        if (!set.isEmpty()) {
            set.forEach(error -> {
                ConstraintViolation constraintViolation = (ConstraintViolation) error;
                stringBuilder.append(constraintViolation.getMessage()).append(",");
            });
            message = stringBuilder.toString().substring(0, stringBuilder.lastIndexOf(","));
        }

        logger.error(message);
        return MallResultVo.failure(Const.API_RETURN_CODE_BAD_REQUEST, message);
    }

    /**
     * 对象校验 抛出 MethodArgumentNotValidException 异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MallResultVo<?> validationBodyException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

        String message = "";
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            fieldErrorList.forEach(error -> {
                stringBuilder.append(error.getField()).append("：")
                        .append(error.getDefaultMessage()).append(",");
            });
            message = stringBuilder.toString().substring(0, stringBuilder.lastIndexOf(","));
        }

        logger.error(message);
        return MallResultVo.failure(Const.API_RETURN_CODE_BAD_REQUEST, message);
    }

    /**
     * 处理 Exception
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public MallResultVo<?> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        String message;
        if (request.getRequestURI().contains(Const.API_URL_PREFIX)) {
            message = Const.API_RETURN_MESSAGE_ERROR;
        } else {
            if (StringUtils.isNotBlank(e.getMessage())) {
                message = e.getMessage();
            } else {
                message = Const.API_RETURN_MESSAGE_ERROR;
            }
        }
        return MallResultVo.failure(Const.API_RETURN_CODE_INTERNAL_SERVER_ERROR, message);
    }

}
