package com.lj.Time.common;

/**
 * 网络请求响应监听接口
 */

public interface NetWorkResponseListener {

    interface OnSuccessResponse<T> {
        void onSuccess(T response);
    }

    interface OnError {
        void onError(String cause);
    }
}
