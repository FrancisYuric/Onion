package com.ciruy.onion_base.rx

fun Boolean.onTrue(trueConsumer: (Boolean) -> Unit): Boolean {
    if (this) trueConsumer.invoke(this)
    return this
}