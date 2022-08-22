package com.bootninza.bullbear.webservices;

public interface RestCallBack<T> {

    void onSuccess(T response);
    void onFailure(Throwable t);
}
