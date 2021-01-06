package com.example.learnkt.ui.baseActivity

import android.util.Pair
import android.view.View
import android.widget.TextView
import com.ciruy.b.heimerdinger.onion.from
import com.ciruy.b.heimerdinger.onion.fromTwo
import com.ciruy.b.heimerdinger.onion.onion
import com.example.learnkt.util.bind2Api
import com.example.learnkt.util.bind2ProgressDownload
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody

open abstract class BaseDisposableActivity : BaseActivity() {

    var mCompositeDisposable: CompositeDisposable? = null

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = CompositeDisposable()
    }

    fun <T> View.bind(flowable: Flowable<T>) =
            from<BaseDisposableActivity, Disposable> {
                addDisposable(it.second)
            }.fromTwo<Disposable, Flowable<T>, Consumer<T>> {
                this.bind2Api(it.first)
                it.first.subscribe(it.second)
            }.invoke {
                flowable
            }


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