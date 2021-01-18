package com.example.learnkt.bean.supcon;



import com.example.learnkt.bean.BaseEntity;

import java.io.Serializable;

/**
 * Created by xushiyun on 2017/8/7.
 */

public class LoginEntity extends BaseEntity implements Serializable{

    public static LoginEntity fail(Throwable throwable){
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.errMsg = throwable.toString();
        loginEntity.success = false;
        return loginEntity;
    }
//    public UserEntity result;
    public boolean success;
    public String errMsg;
}
