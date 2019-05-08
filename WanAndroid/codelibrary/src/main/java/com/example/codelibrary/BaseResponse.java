package com.example.codelibrary;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public class BaseResponse<T> {
    String errorMsg;
    int errorCode;
    T data;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
