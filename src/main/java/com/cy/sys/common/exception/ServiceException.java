package com.cy.sys.common.exception;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 11:50
 * @Description:
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8704299746778117943L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
