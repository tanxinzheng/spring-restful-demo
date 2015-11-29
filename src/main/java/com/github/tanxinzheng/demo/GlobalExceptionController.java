package com.github.tanxinzheng.demo;

import com.github.tanxinzheng.demo.exceptions.NotFoundException;
import com.github.tanxinzheng.demo.exceptions.RestError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestError> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        RestError restError = new RestError(ex, request);
        restError.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<RestError>(restError, HttpStatus.NOT_FOUND);
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
