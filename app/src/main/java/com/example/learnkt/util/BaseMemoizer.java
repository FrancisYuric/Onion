//package com.example.learnkt.util;
//
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentHashMap;
//
//public abstract class BaseMemoizer<T, U>  {
//    protected final Applicable<T, U> applicable;
//    private final Map<T, U> cache = new ConcurrentHashMap<>();
//
//    protected BaseMemoizer(Applicable<T, U> applicable) {
//        this.applicable = applicable;
//    }
//
//    protected U computeIfAbsentOrigin(T t, Applicable<T, U> t2u) {
//        Objects.requireNonNull(t2u);
//        U u;
//        if ((u = cache.get(t)) == null) {
//            U newValue;
//            if ((newValue = t2u.apply(t)) != null) {
//                cache.put(t, newValue);
//                return newValue;
//            }
//        }
//        return u;
//    }
//
//    public void forgetOrigin(T t) {
//        cache.remove(t);
//    }
//}
