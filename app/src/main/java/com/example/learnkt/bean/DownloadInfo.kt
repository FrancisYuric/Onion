package com.example.learnkt.bean

import android.app.DownloadManager
import com.example.learnkt.CiruyApplication
import okhttp3.Request
import okhttp3.Response
import java.io.File

class DownloadInfo(val url:String) {

    companion object{
        public fun createDownInfo(url:String):DownloadInfo{
            val downloadInfo = DownloadInfo(url)
            val contentLength = getContentLength(url)
            downloadInfo.mTotalLength = (contentLength)
            val fileName = url.substring(url.lastIndexOf("/"))
            downloadInfo.mFileName = (fileName)
            return downloadInfo
        }

        private fun getContentLength(url: String): Long {
            val request  = Request.Builder()
                    .url(url)
                    .build()
            return 0L
        }
    }
    lateinit var mFileName:String
    var mDownloadedLength:Long = 0L
    var mTotalLength:Long =  0L
    fun getRealFileName(downloadInfo: DownloadInfo):DownloadInfo
    {
        val fileName:String = downloadInfo.mFileName
        var downloadLength = 0L
        val contentLength = downloadInfo.mTotalLength
        var file = File(CiruyApplication.instance?.applicationContext?.filesDir,fileName)
        if(file.exists())
            downloadLength = file.length()
        var i = 1
        while(downloadLength>=contentLength) {
            val dotIndex = fileName?.lastIndexOf(".")
            val fileNameOther = if(dotIndex==-1)"${fileName}(${i})" else "${fileName.substring(0,dotIndex)}(${i})${fileName.substring(dotIndex)}"
            val newFile = File(CiruyApplication.instance?.filesDir, fileNameOther)
            file = newFile
            downloadLength = newFile.length()
            ++i
        }
        downloadInfo.setProgress(downloadLength)
        downloadInfo.mFileName = file.name
        return downloadInfo
    }

    private fun setProgress(downloadLength: Long) {
        mDownloadedLength = downloadLength
    }
}