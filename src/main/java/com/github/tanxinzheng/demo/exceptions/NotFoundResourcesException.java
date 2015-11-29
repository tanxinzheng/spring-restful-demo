package com.github.tanxinzheng.demo.exceptions;

/**
 * Created by Jeng on 15/11/29.
 * 未找到指定资源，资源已被删除或资源不存在
 */
public class NotFoundResourcesException extends RuntimeException {

    public NotFoundResourcesException() {
        super();
    }

    public NotFoundResourcesException(String message) {
        super(message);
    }

    public NotFoundResourcesException(String message, Throwable cause) {
        super(message, cause);
    }
}
