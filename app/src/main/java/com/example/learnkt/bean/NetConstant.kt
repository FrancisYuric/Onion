package com.example.learnkt.bean

import com.example.learnkt.api.APIClient
import com.example.learnkt.api.SupconApi
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.supcon.LoginEntity
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


fun legalUrl(url: String) = patternCheck(Constant.Pattern.URL_PATTERN, url).matches()

/**
 * Firm 80M安装包下载
 */
fun firm_download() = APIClient.instance()
        .instanceRetrofit(Constant.FIRIM_URL_BASE, WanAndroidAPI::class.java).firApkSize80M()

/**
 * Thunder 20M安装包
 */
fun thunder_download() = APIClient.instance().instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE, WanAndroidAPI::class.java).downloadThunderDmgSize22M()

fun supcon_login(url: String,
                 username: String,
                 password: String) = APIClient.instance()
        .instanceRetrofit(url, SupconApi::class.java)
        .login(username, password, hashMapOf(Pair("machineId", "11111111"),
                Pair("clientType", "mobile"),
                Pair("clientVersion", "2.1"),
                Pair("timeStamp", Date().time.toString()),
                Pair("clientId", "123")))
        .onErrorReturn(LoginEntity::fail)

fun patternCheck(pattern: String, targetStr: String): Matcher =
        Pattern.compile(pattern).matcher(targetStr)
