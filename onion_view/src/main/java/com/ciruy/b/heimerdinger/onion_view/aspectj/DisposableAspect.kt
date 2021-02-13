//package com.ciruy.b.heimerdinger.onion_view.aspectj
//
//import android.util.Log
//import androidx.lifecycle.LifecycleOwner
//import com.ciruy.b.heimerdinger.onion_view.ext.mCompositeDisposable
//import org.aspectj.lang.annotation.Aspect
//import org.aspectj.lang.JoinPoint
//import org.aspectj.lang.annotation.After
//
//
//@Aspect
//class DisposableAspect {
//    // 需要注入的方法
//    //androidx.appcompat.app.AppCompatActivity.onDestroy
//    @After("call(* androidx.appcompat.app.AppCompatActivity.onDestroy())")
//    fun LifecycleOwner.disposeCompositeDisposable() {
//        mCompositeDisposable.dispose()
//    }
//}