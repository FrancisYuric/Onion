package com.example.learnkt.bean.supcon;


import com.example.learnkt.bean.BaseEntity;

import java.io.Serializable;

import io.reactivex.Flowable;

/**
 * Created by xushiyun on 2017/8/7.
 */

public class LoginEntity extends BaseEntity implements Serializable {

    //    public UserEntity result;
    public boolean success;
    public String errMsg;

    public static LoginEntity fail(String errMes) {
        return fail(new Throwable(errMes));
    }

    public static Flowable<LoginEntity> onErrorReturn(String errMsg){
        return Flowable.just(fail(errMsg));
    }

    public static LoginEntity fail(Throwable throwable) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.errMsg = throwable.toString();
        loginEntity.success = false;
        return loginEntity;
    }
}
