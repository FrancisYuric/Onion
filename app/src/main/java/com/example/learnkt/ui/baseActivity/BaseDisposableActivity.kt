package com.example.learnkt.ui.baseActivity

import android.util.Pair
import android.view.View
import android.widget.TextView
import com.ciruy.b.heimerdinger.onion.from
import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity
import com.ciruy.b.heimerdinger.onion_view.bind2Api
import com.ciruy.b.heimerdinger.onion_view.flowableClick
import com.ciruy.b.heimerdinger.onion_view.flowableTextChanges
import com.example.learnkt.bean.DownloadInfo
import com.example.learnkt.manager.DownloadManager
import com.example.learnkt.util.progressDownload
import io.reactivex.Flowable
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
            funAddDisposable().from<Disposable, Flowable<T>, Consumer<T>> {
                val targetFlowable = it.first.observeOn(Schedulers.io())
                this.bind2Api(targetFlowable)
                targetFlowable.subscribe(it.second)
            }.invoke(flowable)

    fun View.download(url: String, consumer: Consumer<DownloadInfo>) =
            funAddDisposable().from<Disposable, Flowable<DownloadInfo>, Consumer<DownloadInfo>> {
                this.bind2Api(it.first)
                        .subscribe(it.second)
            }.invoke(DownloadManager.instance().download(url)).invoke(consumer)

    fun View.cancelDownload(url: String) {
        funAddDisposable().from<Disposable, Flowable<Any>, Any?> {
            it.first.subscribe { DownloadManager.instance().cancel(url) }
        }.invoke(flowableClick(this)).invoke(null)
    }

    fun View.download(flowable: Flowable<ResponseBody>, consumer: Consumer<Pair<String, Int>>) =
            funAddDisposable().from<Disposable, Flowable<ResponseBody>, Consumer<Pair<String, Int>>> {
                this.bind2Api(it.first.subscribeOn(Schedulers.io()).progressDownload()).subscribe(it.second)
            }.invoke(flowable).invoke(consumer)

    fun TextView.flowableTextChanges(consumer: Consumer<CharSequence>) =
            funAddDisposable().from<Disposable, TextView, Consumer<CharSequence>> {
                flowableTextChanges(it.first).subscribe(it.second)
            }.invoke(this).invoke(consumer)

    private fun funAddDisposable(): (Disposable) -> Unit = from { addDisposable(it.second) }


    fun View.flowableClick(consumer: Consumer<Any>) =
            funAddDisposable().from<Disposable, View, Consumer<Any>> { flowableClick(it.first).subscribe(it.second) }.invoke(this).invoke(consumer)

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        super.onDestroy()
    }
}