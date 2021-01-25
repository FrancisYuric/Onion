//package com.example.learnkt.util;
//
//
//import com.example.learnkt.rx.Supplier;
//
//public class StrongMemorizer<T, U> extends BaseMemoizer<T, U> {
//
//    private StrongMemorizer(Applicable<T, U> applicable) {
//        super(applicable);
//    }
//
//    public static <T, U> StrongMemorizer<T, U> applicable(Applicable<T, U> t2u) {
//        return new StrongMemorizer<>(t2u);
//    }
//
//    public Supplier<U> get(T t) {
//        return () -> computeIfAbsentOrigin(t, applicable);
//    }
//
//}
