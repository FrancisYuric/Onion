package com.example.learnkt.manager

import com.example.learnkt.bean.DownloadInfo
import com.example.learnkt.bean.DownloadInfo.Companion.createDownInfo
import com.example.learnkt.rx.DownloadObserser
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.OkHttpClient

class DownloadManager {
    object Holder{
        val INSTANCE  = DownloadManager()
    }
    val mClient = OkHttpClient.Builder().build()
    companion object {
        fun instance():DownloadManager = Holder.INSTANCE
    }

    private val downCalls:HashMap<String,Call> = HashMap()

    //Todo:小心此处的内存泄露
    public fun download(url:String, downloadObserver:DownloadObserser){
        Observable.just(url)
                .filter{  !downCalls.containsKey(it)}
                .flatMap {  Observable.just(createDownInfo(it))}
                .map { this::getRealFileName }
                .
    }

    private fun getRealFileName(downloadInfo:DownloadInfo):DownloadInfo{
        val fileName = downloadInfo.getFilename()
        val downloadLength = 0L
        val contentLength = downloadInfo.
    }

    fun cancel(url:String){
        val call = downCalls[url]
        call?.cancel()
        downCalls.remove(url)
    }

}