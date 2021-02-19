package com.ciruy.b.heimerdinger.onion_view.ext

import android.os.Environment
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion.bean.BaseEntity
import com.ciruy.b.heimerdinger.onion.doIfNotNull
import com.ciruy.b.heimerdinger.onion.from
import com.ciruy.b.heimerdinger.onion.selfNotNull
import com.ciruy.b.heimerdinger.onion_view.memorizer.LifeCycleOwnerMemorizer
import com.ciruy.b.heimerdinger.onion_view.view.bind2Api
import com.ciruy.b.heimerdinger.onion_view.view.flowableClick
import com.ciruy.b.heimerdinger.onion_view.view.flowableTextChanges
import com.ciruy.b.heimerdinger.onion_view.view.lazyBind2Api
import com.ciruy.heimerdinger.onion_net.bean.DownloadInfo
import com.ciruy.heimerdinger.onion_net.bean.ResponseError
import com.ciruy.heimerdinger.onion_net.listener.DownloadApkListener
import com.ciruy.heimerdinger.onion_net.manager.OnionDownloadManager
import com.ciruy.heimerdinger.onion_net.util.RequestUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File

val LifecycleOwner.mCompositeDisposable: CompositeDisposable
    get() = LifeCycleOwnerMemorizer.getIfAbsent(this)

fun LifecycleOwner.addDisposable(disposable: Disposable) {
    mCompositeDisposable.add(disposable)
}

//基于AOP来操作Disposable的回收？
val View.ownerActivity: AppCompatActivity?
    get() = if (this.context is AppCompatActivity) this.context as AppCompatActivity else null

fun LifecycleOwner.funAddDisposable(): (Disposable) -> Unit = from { addDisposable(it.second) }
fun View.flowableClick(consumer: (Any) -> Unit) = this.flowableClick(consumer.toConsumer())

private fun View.flowableClick(consumer: Consumer<Any>) = ownerActivity.doIfNotNull {
    it.funAddDisposable()
            .from<Disposable, View, Consumer<Any>> { pair ->
                flowableClick(pair.first).subscribe(pair.second)
            }.invoke(this).invoke(consumer)
}

@Deprecated("disposable may cause memory leak", ReplaceWith("flowableClick(this).subscribe(consumer)", "com.ciruy.b.heimerdinger.onion_view.view.flowableClick"))
fun View.flowableClickUnsafe(consumer: Consumer<Any>) =
        flowableClick(this).subscribe(consumer)!!

@Deprecated("disposable may cause memory leak", ReplaceWith("flowableClick(this).subscribe(consumer.toConsumer())", "com.ciruy.b.heimerdinger.onion_view.view.flowableClick", "com.example.learnkt.util.toConsumer"))
fun View.flowableClickUnsafe(consumer: (Any) -> Unit) =
        flowableClick(this).subscribe(consumer.toConsumer())!!

fun <T> ((T) -> Unit).toConsumer() = Consumer<T> { this.invoke(it) }
fun TextView.flowableTextChanges(consumer: (CharSequence) -> Unit) = this.flowableTextChanges(consumer.toConsumer())
private fun TextView.flowableTextChanges(consumer: Consumer<CharSequence>) =
        ownerActivity.doIfNotNull {
            it.funAddDisposable().from<Disposable, TextView, Consumer<CharSequence>> { pair ->
                flowableTextChanges(pair.first).subscribe(pair.second)
            }.invoke(this).invoke(consumer)
        }


fun <T> View.lazyBind(flowable: () -> Flowable<T>) = ownerActivity.selfNotNull {
    it.funAddDisposable()
            .from<Disposable, () -> Flowable<T>, (T) -> Unit> { pair ->
                this.lazyBind2Api(flowable).subscribe(pair.second.toConsumer())
            }
            .invoke(flowable)
}

fun <T> View.lazyBind(flowable: () -> Flowable<T>, consumer: (T) -> Unit) =
        this.lazyBind(flowable)?.invoke(consumer)

fun <T> View.bind(flowable: Flowable<T>) =
        ownerActivity.selfNotNull {
            it.funAddDisposable().from<Disposable, Flowable<T>, Consumer<T>> { pair ->
                val targetFlowable = pair.first.observeOn(Schedulers.io())
                this.bind2Api(targetFlowable)
                targetFlowable.subscribe(pair.second)
            }.invoke(flowable)
        }

fun <T> View.bind(flowable: Flowable<T>, consumer: (T) -> Unit) =
        this.bind(flowable)?.invoke(consumer.toConsumer())

fun View.download(url: String, consumer: (DownloadInfo) -> Unit) = this.download(url, consumer.toConsumer())
private fun View.download(url: String, consumer: Consumer<DownloadInfo>) = ownerActivity.doIfNotNull {
    it.funAddDisposable().from<Disposable, Flowable<DownloadInfo>, Consumer<DownloadInfo>> {pair->
        this.bind2Api(pair.first)
                .subscribe(pair.second)
    }.invoke(OnionDownloadManager.instance().download(url)).invoke(consumer)
}

fun View.cancelDownload(url: String) = ownerActivity.doIfNotNull {
    it.funAddDisposable().from<Disposable, Flowable<Any>, Any?> {pair->
        pair.first.subscribe { OnionDownloadManager.instance().cancel(url) }
    }.invoke(flowableClick(this)).invoke(null)
}

fun View.download(flowable: Flowable<ResponseBody>, consumer: (Pair<String, Int>) -> Unit) =
        this.download(flowable, consumer.toConsumer())


private fun View.download(flowable: Flowable<ResponseBody>, consumer: Consumer<Pair<String, Int>>) =
        ownerActivity.doIfNotNull {
            it.funAddDisposable().from<Disposable, Flowable<ResponseBody>, Consumer<Pair<String, Int>>> {pair->
                this.bind2Api(pair.first.subscribeOn(Schedulers.io()).progressDownload())
                        .subscribe(pair.second)
            }.invoke(flowable).invoke(consumer)
        }
fun View.bind2ProgressDownload(flowable: Flowable<ResponseBody>): Flowable<Pair<String, Int>> = this.bind2Api(flowable).progressDownload()



fun <T : BaseEntity> errorFlowable(errMes: String) = Flowable.error<T>(Throwable(errMes))
fun Flowable<ResponseBody>.progressDownload(): Flowable<Pair<String, Int>> = this.observeOn(Schedulers.io())
        .onErrorReturn { ResponseError.error(it) }
        .flatMap { t: ResponseBody ->
            if (t is ResponseError) Flowable.just(Pair(t.throwable.toString(), -1))
            else object : Observable<Pair<String, Int>>() {
                override fun subscribeActual(observer: Observer<in Pair<String, Int>>?) {
                    val downloadApkListener = DownloadApkListener(observer)
                    observer?.onSubscribe(downloadApkListener)
                    RequestUtil.writeResponseBodyToDisk(t, "${OnionDownloadManager.instance().mApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}${File.separator}"
                            + System.currentTimeMillis() + ".apk", downloadApkListener)
                }
            }.toFlowable(BackpressureStrategy.DROP)
        }
        .observeOn(AndroidSchedulers.mainThread())