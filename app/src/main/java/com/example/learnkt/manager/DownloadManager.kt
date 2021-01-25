package com.example.learnkt.manager

import com.example.learnkt.CiruyApplication
import com.example.learnkt.bean.DownloadInfo
import com.example.learnkt.rx.DownloadObserver
import com.ciruy.onion_base.util.LogUtil
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class DownloadManager {
    object Holder {
        val INSTANCE = DownloadManager()
    }

    val mClient = OkHttpClient.Builder().build()

    companion object {
        fun instance(): DownloadManager = Holder.INSTANCE
    }

    private fun createDownInfo(url: String) = DownloadInfo(url, getContentLength(url), 0, url.substring(url.lastIndexOf("/")))
    private val downCalls: HashMap<String, Call> = HashMap()

    //Todo:小心此处的内存泄露
    fun download_(url: String, downloadObserver: DownloadObserver) {
        Observable.just(url)
                .filter { !downCalls.containsKey(it) }
                .flatMap { Observable.just(createDownInfo(it)) }
                .map { getRealFileName(it) }
                .flatMap { Observable.create(DownloadSubscribe(it)) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(downloadObserver)
    }

    fun download(url: String) = Observable.just(url)
            .filter { !downCalls.containsKey(it) }
            .flatMap { Observable.just(createDownInfo(it)) }
            .map { getRealFileName(it) }
            .flatMap { Observable.create(DownloadSubscribe(it)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.DROP)

    private fun getRealFileName(downloadInfo: DownloadInfo): DownloadInfo {
        val fileName: String = downloadInfo.mFileName
        var downloadLength = 0L
        val contentLength = downloadInfo.total
        var file = File(CiruyApplication.instance?.applicationContext?.filesDir, fileName)
        if (file.exists())
            downloadLength = file.length()
        var i = 1
        while (downloadLength >= contentLength) {
            val dotIndex = fileName.lastIndexOf(".")
            val fileNameOther = if (dotIndex == -1) "${fileName}(${i})" else "${fileName.substring(0, dotIndex)}(${i})${fileName.substring(dotIndex)}"
            val newFile = File(CiruyApplication.instance?.filesDir, fileNameOther)
            file = newFile
            downloadLength = newFile.length()
            ++i
        }
        downloadInfo.progress = downloadLength
        downloadInfo.mFileName = file.name
        return downloadInfo
    }


    fun cancel(url: String) {
        val call = downCalls[url]
        call?.cancel()
        downCalls.remove(url)
    }

    private fun getContentLength(url: String): Long {
        val request = Request.Builder()
                .url(url)
                .build()
        try {
            val response = mClient.newCall(request).execute()
            if (response.isSuccessful) {
                val contentLength = response.body?.contentLength()
                response.close()
                return if (contentLength == 0L) DownloadInfo.TOTAL_ERROR else contentLength
                        ?: DownloadInfo.TOTAL_ERROR
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return DownloadInfo.TOTAL_ERROR
    }

    inner class DownloadSubscribe(private val downloadInfo: DownloadInfo) : ObservableOnSubscribe<DownloadInfo> {
        override fun subscribe(emitter: ObservableEmitter<DownloadInfo>) {
            LogUtil.e("url subscribed")
            val url = downloadInfo.url
            var downloadedLength = downloadInfo.progress
            val totalLength = downloadInfo.total
            emitter.onNext(downloadInfo)

            val request = Request.Builder()
                    .addHeader("RANGE", "bytes=$downloadedLength-$totalLength")
                    .url(url)
                    .build()

            val call = mClient.newCall(request)
            downCalls[url] = call
            val response = call.execute()
            val file = File(CiruyApplication.instance?.filesDir, downloadInfo.mFileName)
            var inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null
            try {
                inputStream = response.body?.byteStream()
                outputStream = FileOutputStream(file, true)
                val buffer = ByteArray(4096)
                while (true) {
                    val len = inputStream?.read(buffer)
                    if (len == -1) break
                    outputStream.write(buffer, 0, len ?: 0)
                    downloadedLength += (len ?: 0)
                    downloadInfo.progress = downloadedLength
                    emitter.onNext(downloadInfo)
                }
                outputStream.flush()
                downCalls.remove(url)
            }catch (e:Exception){
//                emitter.onError(Throwable(e.message))
//                emitter.onNext()
            }
            finally {
                inputStream?.close()
                outputStream?.close()
            }
            emitter.onComplete()
        }
    }
}

