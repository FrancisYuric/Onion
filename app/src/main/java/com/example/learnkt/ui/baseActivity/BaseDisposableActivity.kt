package com.example.learnkt.ui.baseActivity

import android.view.View
import android.widget.TextView
import androidx.core.util.Pair
import com.ciruy.b.heimerdinger.onion.from
import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity
import com.ciruy.b.heimerdinger.onion_view.ext.addDisposable
import com.ciruy.b.heimerdinger.onion_view.ext.mCompositeDisposable
import com.ciruy.b.heimerdinger.onion_view.view.bind2Api
import com.ciruy.b.heimerdinger.onion_view.view.flowableClick
import com.ciruy.b.heimerdinger.onion_view.view.flowableTextChanges
import com.ciruy.b.heimerdinger.onion_view.view.lazyBind2Api
import com.example.learnkt.bean.DownloadInfo
import com.example.learnkt.manager.DownloadManager
import com.example.learnkt.util.progressDownload
import com.example.learnkt.util.toConsumer
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

abstract class BaseDisposableActivity(override var layout: Int?) : BaseActivity(layout) {
    constructor() : this(null)

    fun <T> View.lazyBind(flowable: () -> Flowable<T>) = funAddDisposable()
            .from<Disposable, () -> Flowable<T>, (T) -> Unit> { this.lazyBind2Api(flowable).subscribe(it.second.toConsumer()) }
            .invoke(flowable)

    fun <T> View.lazyBind(flowable: () -> Flowable<T>, consumer: (T) -> Unit) =
            this.lazyBind(flowable).invoke(consumer)

    fun <T> View.bind(flowable: Flowable<T>) =
            funAddDisposable().from<Disposable, Flowable<T>, Consumer<T>> {
                val targetFlowable = it.first.observeOn(Schedulers.io())
                this.bind2Api(targetFlowable)
                targetFlowable.subscribe(it.second)
            }.invoke(flowable)

    fun <T> View.bind(flowable: Flowable<T>, consumer: (T) -> Unit) =
            this.bind(flowable).invoke(consumer.toConsumer())

    fun View.download(url: String, consumer: (DownloadInfo) -> Unit) = this.download(url, consumer.toConsumer())
    private fun View.download(url: String, consumer: Consumer<DownloadInfo>) = funAddDisposable().from<Disposable, Flowable<DownloadInfo>, Consumer<DownloadInfo>> {
        this.bind2Api(it.first)
                .subscribe(it.second)
    }.invoke(DownloadManager.instance().download(url)).invoke(consumer)

    fun View.cancelDownload(url: String) = funAddDisposable().from<Disposable, Flowable<Any>, Any?> {
        it.first.subscribe { DownloadManager.instance().cancel(url) }
    }.invoke(flowableClick(this)).invoke(null)

    fun View.download(flowable: Flowable<ResponseBody>, consumer: (Pair<String, Int>) -> Unit) =
            this.download(flowable, consumer.toConsumer())


    private fun View.download(flowable: Flowable<ResponseBody>, consumer: Consumer<Pair<String, Int>>) =
            funAddDisposable().from<Disposable, Flowable<ResponseBody>, Consumer<Pair<String, Int>>> {
                this.bind2Api(it.first.subscribeOn(Schedulers.io()).progressDownload())
                        .subscribe(it.second)
            }.invoke(flowable).invoke(consumer)

    fun TextView.flowableTextChanges(consumer: (CharSequence) -> Unit) = this.flowableTextChanges(consumer.toConsumer())
    private fun TextView.flowableTextChanges(consumer: Consumer<CharSequence>) =
            funAddDisposable().from<Disposable, TextView, Consumer<CharSequence>> {
                flowableTextChanges(it.first).subscribe(it.second)
            }.invoke(this).invoke(consumer)

    fun funAddDisposable(): (Disposable) -> Unit = from { addDisposable(it.second) }

    fun View.flowableClick(consumer: (Any) -> Unit) = this.flowableClick(consumer.toConsumer())

    private fun View.flowableClick(consumer: Consumer<Any>) =
            funAddDisposable().from<Disposable, View, Consumer<Any>> { flowableClick(it.first).subscribe(it.second) }.invoke(this).invoke(consumer)

    @Deprecated("disposable may cause memory leak", ReplaceWith("flowableClick(this).subscribe(consumer)", "com.ciruy.b.heimerdinger.onion_view.view.flowableClick"))
    fun View.flowableClickUnsafe(consumer: Consumer<Any>) =
            flowableClick(this).subscribe(consumer)!!

    @Deprecated("disposable may cause memory leak", ReplaceWith("flowableClick(this).subscribe(consumer.toConsumer())", "com.ciruy.b.heimerdinger.onion_view.view.flowableClick", "com.example.learnkt.util.toConsumer"))
    fun View.flowableClickUnsafe(consumer: (Any) -> Unit) =
            flowableClick(this).subscribe(consumer.toConsumer())!!

    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }
}