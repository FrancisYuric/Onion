package com.example.learnkt.util;

import android.os.Environment;
import android.util.Log;


import com.example.learnkt.listener.DownloadApkListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class RequestUtil {

    private final static String LOG_SAVE_DIR = "日志信息";

    /**
     * 日志模式开关,毕竟是需要进行IO操作,很多时候我并不希望将其打开浪费性能
     **/
    private static final Boolean LOG_MODE = false;

//    /**
//     * 对于请求数据信息的保存操作
//     *
//     * @param requestName   请求名称以及对应的请求信息
//     * @param requestParams 请求的参数
//     */
//    public static void saveRequestInfo(String requestName, String... requestParams) throws IOException {
//        if (!LOG_MODE) return;
//        File sdCardDir = new File(Environment.getExternalStorageDirectory(), LOG_SAVE_DIR);
//        if (!sdCardDir.exists() && !sdCardDir.mkdir()) {
//            throw new IOException("创建日志文件夹失败!");
//        }
//        File tarLogDir = new File(sdCardDir.getAbsolutePath(), requestName);
//        if (!tarLogDir.exists() && !tarLogDir.mkdir()) {
//            throw new IOException("创建对应请求文件夹失败!");
//        }
//        File logFile = new File(tarLogDir, requestName + "-" + DateUtil.dateFormat(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + ".txt");
//        if (!logFile.exists() && !logFile.createNewFile())
//            throw new IOException("创建日志文件失败!");
//        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(logFile));
//        for (int i = 0; i < requestParams.length; ++i) {
//            String param = requestParams[i];
//            outputStream.write(((i == requestParams.length - 1 ? "请求结果" : "请求参数") + i + ":\n" + param + "\n\n").getBytes());
//        }
//        outputStream.flush();
//        outputStream.close();
//    }

    public static void writeResponseBodyToDisk(ResponseBody body, String targetFilePath, final DownloadApkListener downloadApkListener) {
        if (downloadApkListener != null)
            downloadApkListener.onStart();
        try {
            File file = new File(targetFilePath);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            InputStream inputStream;
            OutputStream outputStream;
            byte[] fileReader = new byte[4096];
            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);
            int progressPercent=0;
            LogUtil.INSTANCE.e("write start!");
            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
                int newProgressPercent = (int) (100*fileSizeDownloaded/fileSize);
                if (newProgressPercent - progressPercent >= 2) {
                    LogUtil.INSTANCE.e(fileSizeDownloaded + " totalSize:" + fileSize);
                    progressPercent = newProgressPercent;
                    if (downloadApkListener != null)
                        downloadApkListener.onProgress(progressPercent);
                }
            }

            if (downloadApkListener != null)
                downloadApkListener.onFinish();
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (downloadApkListener != null)
                downloadApkListener.onError("" + e.getMessage());
        }
    }
}
