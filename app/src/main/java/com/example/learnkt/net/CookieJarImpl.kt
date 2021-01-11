package com.example.learnkt.net

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object CookieJarImpl : CookieJar {
    private var cache: List<Cookie> = arrayListOf()
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.plus(cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        cache = cache.filter { it.expiresAt < System.currentTimeMillis() || it.matches(url) }
        return cache
    }
}
