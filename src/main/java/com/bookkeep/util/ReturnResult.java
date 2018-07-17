package com.bookkeep.util;

/**
 * Created by yht on 2018/3/10.
 */
public class ReturnResult<T> {
    private String message;
    private boolean state;
    private T data;

    private ReturnResult(boolean state, T data) {
        this.state = state;
        this.data = data;
    }

    private ReturnResult(boolean state, String msg) {
        this.state = state;
        this.message = msg;
    }

    private ReturnResult(CodeMsg cm) {
        if (cm == null) {
            return;
        }
//        this.state = cm.getRetCode();
        this.message = cm.getMessage();
    }

    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> ReturnResult<T> success(T data) {
        boolean state = true;
        return new ReturnResult<T>(state, data);
    }

    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> ReturnResult<T> success(String msg) {
        boolean state = true;
        return new ReturnResult<T>(state, msg);
    }

    /**
     * 成功，不需要传入参数
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> ReturnResult<T> success() {
        return (ReturnResult<T>) success("");
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> ReturnResult<T> error(CodeMsg cm) {
        return new ReturnResult<T>(cm);
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> ReturnResult<T> error(String msg) {
        boolean state = false;
        return new ReturnResult<T>(state, msg);
    }

    /**
     * 失败时候的调用,扩展消息参数
     *
     * @param cm
     * @param msg
     * @return
     */
    public static <T> ReturnResult<T> error(CodeMsg cm, String msg) {
        cm.setMessage(cm.getMessage() + "--" + msg);
        return new ReturnResult<T>(cm);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean getstate() {
        return state;
    }
}
