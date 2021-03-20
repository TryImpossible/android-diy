package com.barry.baselib.network.response;

import androidx.annotation.Nullable;

/**
 * 网络请求结果异常
 */
public class ResponseException extends RuntimeException {
    // 异常状态
    private int errorCode;
    // 异常信息
    private String errorMessage;
    // api名称
    private String apiName;

    public ResponseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResponseException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResponseException(int errorCode, String errorMessage, String apiName) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.apiName = apiName;
    }

    public ResponseException(ResponseResult response) {
        if (null != response) {
            this.errorCode = response.getCode();
            this.errorMessage = response.getMessage();
        }
    }

    public ResponseException(ResponseResult response, String apiName) {
        if (null != response) {
            this.errorCode = response.getCode();
            this.errorMessage = response.getMessage();
        }
        this.apiName = apiName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @Nullable
    @Override
    public String getMessage() {
        if (null != errorMessage) {
            return errorMessage;
        } else {
            return String.valueOf(errorCode);
        }
    }
}
