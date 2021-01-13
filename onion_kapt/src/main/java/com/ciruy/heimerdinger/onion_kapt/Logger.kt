package com.ciruy.heimerdinger.onion_kapt

import javax.annotation.processing.Messager
import javax.tools.Diagnostic

class Logger(val mMsg: Messager) {
    private val PREFIX = "KAPT_LOGGER"
    private fun print(kind: Diagnostic.Kind, info: CharSequence?) {
        if (!info.isNullOrBlank())
            mMsg.printMessage(kind, "$PREFIX $info")
    }

    fun info(info: CharSequence?) {
        print(Diagnostic.Kind.NOTE, ">>> $info <<<")
    }

    fun warning(warnings: CharSequence?) {
        print(Diagnostic.Kind.WARNING, "### $warnings ###")
    }

    fun error(error: CharSequence?) {
        print(Diagnostic.Kind.ERROR, "There is an error [$error]")
    }

    fun error(error: Throwable) {
        print(Diagnostic.Kind.ERROR, "There is an error [${error.message}]\n ${formatStackTrace(error.stackTrace)}")
    }

    private fun formatStackTrace(stackTrace: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (element in stackTrace) {
            sb.append("    at ").append(element.toString()).append("\n")
        }
        return sb.toString()
    }
}