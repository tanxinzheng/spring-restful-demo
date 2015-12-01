package com.github.tanxinzheng.demo;

import com.github.tanxinzheng.demo.exceptions.ArgumentValidaErrorException;
import com.github.tanxinzheng.demo.exceptions.NotFoundResourcesException;
import com.github.tanxinzheng.demo.exceptions.RestError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jeng on 15/11/29.
 */
@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    private static final Log logger = LogFactory
            .getLog(GlobalExceptionController.class);

//    @ExceptionHandler(SQLException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleSQLException(HttpServletRequest request,
//                                           SQLException ex) {
//        handleLog(request, ex);
//        Map<String, Object> errorMap = new HashMap<String, Object>();
//        errorMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
//        errorMap.put("Requested", request.getRequestURL());
//        errorMap.put("message", ex.toString());
//
//        return new ModelAndView(JSONUtils.VIEW_NAME,
//                JSONStringView.JSON_MODEL_DATA, errorMap);
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ModelAndView handleBadRequestException(
//            HttpServletRequest request, BadRequestException ex) {
//        handleLog(request, ex);
//        ExceptionModel exceptionModel = getExceptionModel(
//                HttpStatus.BAD_REQUEST, ex);
//        return new ModelAndView(JSONUtils.VIEW_NAME,
//                JSONStringView.JSON_MODEL_DATA, exceptionModel);
//    }

    @ExceptionHandler(NotFoundResourcesException.class)
    public ResponseEntity<RestError> handleNotFoundException(NotFoundResourcesException ex, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<RestError>(restError, HttpStatus.NOT_FOUND);
    }

    MessageSource messageSource;

    @ExceptionHandler(ArgumentValidaErrorException.class)
    public ResponseEntity processValidationError(ArgumentValidaErrorException ex, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.BAD_REQUEST);
        restError.setMessage("非法请求参数，校验请求参数不合法");
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        restError.setErrors(fieldErrors);
        return new ResponseEntity<RestError>(restError, HttpStatus.BAD_REQUEST);
    }
    // 国际化错误信息应该异常中处理
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleAllException(HttpServletRequest request,
//                                           Exception ex) {
//
//        handleLog(request, ex);
//        Map<String, Object> errorMap = new HashMap<String, Object>();
//        errorMap.put("code",
//                Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        errorMap.put("message", ex.toString());
//        return new ModelAndView(JSONUtils.VIEW_NAME,
//                JSONStringView.JSON_MODEL_DATA, errorMap);
//
//    }

//    private ExceptionModel getExceptionModel(HttpStatus httpStatus,
//                                             CommonException ex) {
//        ExceptionModel exceptionModel = new ExceptionModel();
//        ErrorENUM errorEnum = ex.getErrorEnum();
//        exceptionModel.setStatus(httpStatus.value());
//        exceptionModel.setMoreInfo(ex.getMoreInfo());
//        if (errorEnum != null) {
//            exceptionModel.setErrorCode(errorEnum.getCode());
//            exceptionModel.setMessage(errorEnum.toString());
//        }
//        return exceptionModel;
//    }

//    private void handleLog(HttpServletRequest request, Exception ex) {
//        Map parameter = request.getParameterMap();
//        StringBuffer logBuffer = new StringBuffer();
//        if (request != null) {
//            logBuffer.append("  request method=" + request.getMethod());
//            logBuffer.append("  url=" + request.getRequestURL());
//        }
//        if (ex instanceof CommonException) {
//            logBuffer.append("  moreInfo="
//                    + ((CommonException) ex).getMoreInfo());
//        }
//        if (ex != null) {
//            logBuffer.append("  exception:" + ex);
//        }
//        logger.error(logBuffer.toString());
//    }
}
