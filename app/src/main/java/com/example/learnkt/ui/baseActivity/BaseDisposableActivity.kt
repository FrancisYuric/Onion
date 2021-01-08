package com.example.learnkt.ui.baseActivity

import android.util.Pair
import android.view.View
import android.widget.TextView
import com.ciruy.b.heimerdinger.onion.from
import com.ciruy.b.heimerdinger.onion_view.bind2Api
import com.ciruy.b.heimerdinger.onion_view.flowableTextChanges
import com.example.learnkt.util.progressDownload
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

abstract class BaseDisposableActivity : BaseActivity() {

    var mCompositeDisposable: CompositeDisposable? = null

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(disposable)
    }

    fun <T> View.bind(flowable: Flowable<T>) =
            from<BaseDisposableActivity, Disposable> {
                addDisposable(it.second)
            }.from<Disposable, Flowable<T>, Consumer<T>> {
                val targetFlowable = it.first.observeOn(Schedulers.io())
                this.bind2Api(targetFlowable)
                targetFlowable.subscribe(it.second)
            }.invoke(flowable)

    fun View.download(flowable: Flowable<ResponseBody>,consumer: Consumer<Pair<String,Int>>) =
            from<BaseDisposableActivity, Disposable> {
                addDisposable(it.second)
            }.from<Disposable, Flowable<ResponseBody>, Consumer<Pair<String, Int>>> {
                this.bind2Api(it.first.subscribeOn(Schedulers.io()).progressDownload()).subscribe(it.second)
            }.invoke(flowable).invoke(consumer)

    fun TextView.textChanges(consumer: Consumer<CharSequence>) =
        from<BaseDisposableActivity, Disposable>{
            addDisposable(it.second)
        }.from<Disposable,TextView,Consumer<CharSequence>>{
            flowableTextChanges(it.first).subscribe(it.second)
        }.invoke(this).invoke(consumer)
//    fun View.bind(vConnect2Disposable: ((Pair<View, Disposable>) -> Unit)): (() -> Disposable) -> Unit {
//        return { it ->
//            vConnect2Disposable.invoke(Pair(this, it.invoke()))
//        }
//    }
//
//    fun <T> ((() -> Disposable)).bind(disposable2Flowable: (Pair<Disposable, Flowable<T>>) -> Unit): (() -> Flowable<T>) -> Unit {
//        return {
//            disposable2Flowable.invoke(Pair(this.invoke(), it.invoke()))
//        }
//    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        super.onDestroy()
    }
}