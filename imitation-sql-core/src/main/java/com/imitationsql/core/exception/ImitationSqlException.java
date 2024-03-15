package com.imitationsql.core.exception;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 13:43
 */
public class ImitationSqlException extends RuntimeException {

    private String message;

    public ImitationSqlException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
