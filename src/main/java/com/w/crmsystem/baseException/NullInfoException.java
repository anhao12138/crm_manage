package com.w.crmsystem.baseException;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 15:34
 * @Version 1.0
 */
public class NullInfoException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NullInfoException(String message) {
        super(message);
    }
}
