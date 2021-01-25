//package com.example.learnkt.util;
//
//import androidx.annotation.Nullable;
//
//import com.example.learnkt.rx.Supplier;
//
//import java.lang.ref.SoftReference;
//
//public class SoftMemoizer<T, U>
//        extends BaseMemoizer<SoftMemoizer.ComparableSoftReference<T>,
//        SoftMemoizer.ComparableSoftReference<U>> {
//
//    private SoftMemoizer(Applicable<ComparableSoftReference<T>,
//                        ComparableSoftReference<U>> applicable) {
//        super(applicable);
//    }
//
//    public static <T, U> SoftMemoizer<T, U> applicable(Applicable<T, U> applicable) {
//        return new SoftMemoizer<>(applicable.from(unWrapReference()).to(softReference()));
//    }
//
//    private static <U> Applicable<U, ComparableSoftReference<U>> softReference() {
//        return ComparableSoftReference::new;
//    }
//
//    private static <U> Applicable<ComparableSoftReference<U>, U> unWrapReference() {
//        return SoftReference::get;
//    }
//
//    public Supplier<U> get(T t) {
//        return () -> computeIfAbsentOrigin(SoftMemoizer.<T>softReference().apply(t), applicable).get();
//    }
//
//    protected static class ComparableSoftReference<T> extends SoftReference<T> {
//
//        private ComparableSoftReference(T referent) {
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
//            if (obj instanceof ComparableSoftReference)
//                return get().equals(((ComparableSoftReference) obj).get());
//            return false;
//        }
//    }
//
//}
