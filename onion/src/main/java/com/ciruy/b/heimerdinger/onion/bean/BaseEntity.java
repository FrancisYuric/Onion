package com.ciruy.b.heimerdinger.onion.bean;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    static final long serialVersionUID = 536871008L;


    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
