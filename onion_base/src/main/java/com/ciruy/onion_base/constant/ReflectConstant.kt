package com.ciruy.onion_base.constant

import com.ciruy.b.heimerdinger.onion.self
import java.lang.reflect.Field

object Method {
    const val AssetManager_addAssetPath = "addAssetPath"
}

object Field {
    const val LayoutInflater_mFactorySet = "mFactorySet"
}

fun <T : Any> T.declaredField(clazz: Class<*>, fieldName: String): Field =
        clazz.getDeclaredField(fieldName)
                .self { it.isAccessible = true }

fun <T : Any> T.set(clazz: Class<*>, fieldName: String, value: Any?) =
        declaredField(clazz, fieldName).set(this, value)

fun <T : Any> T.set(fieldName: String, value: Any?) =
        this.declaredField(this.javaClass, fieldName).set(this, value)

fun <T : Any> T.method(methodName: String, paramArray: Array<Class<*>>) =
        this.javaClass.getMethod(methodName, *paramArray)

fun <T : Any> T.method(clazz: Class<*>, methodName: String, paramArray: Array<Class<*>>) =
        clazz.getMethod(methodName, *paramArray)
