package com.ciruy.onion_base.rx

fun Boolean.onTrue(trueConsumer: (Boolean) -> Unit): Boolean {
    if (this) trueConsumer.invoke(this)
    return this
}

fun Boolean.onFalse(falseConsumer: (Boolean) -> Unit): Boolean {
    if (!this) falseConsumer.invoke(this)
    return this
}