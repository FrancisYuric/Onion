package com.example.learnkt.net

import ciruy.b.heimerdinger.annotation.ResponseFormat
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type

class JsonOrXmlConverterFactory : Converter.Factory() {
    val xmlFactory: Converter.Factory by lazy { SimpleXmlConverterFactory.create() }
    val jsonFactory: Converter.Factory by lazy {
        GsonConverterFactory.create(
                GsonBuilder().setLenient().setDateFormat(
                        "yyyy-MM-dd'T'HH:mm:ssZ"
                ).serializeNulls().create()
        )
    }

    companion object {
        fun create(): JsonOrXmlConverterFactory = JsonOrXmlConverterFactory()
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation !is ResponseFormat) continue
            val value = annotation.value
            if (ResponseFormat.JSON == value) return jsonFactory.responseBodyConverter(type, annotations, retrofit)
            else if (ResponseFormat.XML == value) return xmlFactory.responseBodyConverter(type, annotations, retrofit)
        }
        return null
    }

}