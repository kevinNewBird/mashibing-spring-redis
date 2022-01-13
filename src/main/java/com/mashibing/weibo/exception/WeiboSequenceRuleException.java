package com.mashibing.weibo.exception;

/**
 * description  微博频次限制异常 <BR>
 * <p>
 * author: zhao.song
 * date: created in 11:46  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class WeiboSequenceRuleException extends RuntimeException{


    private Class<?> returnType;


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WeiboSequenceRuleException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public WeiboSequenceRuleException(String message, Throwable cause) {
        super(message, cause);
    }

}
