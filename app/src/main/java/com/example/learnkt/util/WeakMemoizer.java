//package com.example.learnkt.util;
//
//import androidx.annotation.Nullable;
//
//import com.example.learnkt.rx.Supplier;
//
//import java.lang.ref.WeakReference;
//
//public class WeakMemoizer<T, U> extends BaseMemoizer<WeakMemoizer.ComparableWeakReference<T>, WeakMemoizer.ComparableWeakReference<U>> {
//
//    private WeakMemoizer(Applicable<ComparableWeakReference<T>, ComparableWeakReference<U>> applicable) {
//        super(applicable);
//    }
//
//    public static <T, U> WeakMemoizer<T, U> applicable(Applicable<T, U> applicable) {
//        return new WeakMemoizer<>(applicable.from(unWrapReference()).to(weakReference()));
//    }
//
//    private static <T> Applicable<T, ComparableWeakReference<T>> weakReference() {
//        return ComparableWeakReference::new;
//    }
//
//    private static <T> Applicable<ComparableWeakReference<T>, T> unWrapReference() {
//        return ComparableWeakReference::get;
//    }
//
//    public Supplier<U> get(T t) {
//        return () -> computeIfAbsentOrigin(WeakMemoizer.<T>weakReference().apply(t), applicable).get();
//    }
//
//    protected static class ComparableWeakReference<T> extends WeakReference<T> {
//
//        ComparableWeakReference(T referent) {
//            super(referent);
//        }
//
//        @Override
//        public int hashCode() {
//            return get().hashCode();
//        }
//
//        @Override
//        public boolean equals(@Nullable Object obj) {
//            if (obj instanceof ComparableWeakReference)
//                return get().equals(((ComparableWeakReference) obj).get());
//            return false;
//        }
//    }
