package com.barry.baselib.network.response;


import com.barry.baselib.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ResponseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        ResponseResult result = (ResponseResult) t;
        // TODO：兼容服务端code 400，对于客户端是成功的
        if ((result).isSuccess() || result.getCode() == 400) {
            onSuccess(t);
        } else {
            throw new ResponseException(result);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e.getMessage());
        // Http错误
//        if (e instanceof HttpException) {
//            ToastUtils.showShort(R.string.basic_bad_network);
//            // 连接错误
//        } else if (e instanceof ConnectException
//                || e instanceof UnknownHostException) {
//            ToastUtils.showShort(R.string.basic_connect_error);
//            // 连接超时
//        } else if (e instanceof InterruptedIOException) {
//            ToastUtils.showShort(R.string.basic_connect_timeout);
//            // 解析错误
//        } else if (e instanceof JsonParseException
//                || e instanceof ParseException
//                || e instanceof JSONException) {
//            ToastUtils.showShort(R.string.basic_parse_error);
//            // 服务器返回错误
//        } else if (e instanceof ResponseException) {
//            ToastUtils.showShort(e.getMessage());
//        } else {
//            // 未知错误
//            ToastUtils.showShort(R.string.basic_unknown_error);
//        }
        onFailure(e);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 数据请求成功
     *
     * @param data 服务端返回数据
     */
    public abstract void onSuccess(T data);

    /**
     * 数据请求失败
     *
     * @param e 失败信息
     */
    public abstract void onFailure(Throwable e);

}
