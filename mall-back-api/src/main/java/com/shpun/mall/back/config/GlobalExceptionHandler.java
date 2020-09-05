package com.shpun.mall.back.config;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.vo.MallResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 全局异常处理
 * @Author: sun
 * @Date: 2020/8/29 11:15
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
     * 处理 Exception
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public MallResultVo<?> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());

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
