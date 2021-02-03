package com.ciruy.b.heimerdinger.onion

import java.lang.ref.WeakReference


fun <F> F.weakR() = WeakReference(this)
fun <F, T, P> F.onion(pairConsumer: (Pair<F, T>) -> P): (() -> (T)) -> P = {
    pairConsumer.invoke(Pair(this, it.invoke()))
}

fun <F, T, P> ((T?) -> P).changeFrom(f2t: (F) -> T?): (F) -> P = {
    this.invoke(f2t.invoke(it))
}

fun <F, T, P> ((F) -> T).changeTo(t2p: (T) -> P): (F) -> P = {
    t2p.invoke(this.invoke(it))
}

fun <F, T> F.from(pairConsumer: (Pair<F, T>) -> Unit): (T) -> Unit {
    return {
        pairConsumer.invoke(Pair(this, it))
    }
}

/**
 * Activity.addDisposable(disposable):((()->disposable)->Unit)
 * (()->disposable)->Unit
 */
fun <F, T, P> ((F) -> Unit).from(pair2f: (Pair<T, P>) -> F): (T) -> (P) -> Unit {
    return { t ->
        { p ->
            this.invoke(pair2f.invoke(Pair(t, p)))
        }
    }
}



fun <T : Any?> T.createIfNull(tProvider: () -> T?) = this ?: tProvider.invoke()

fun <T : Any?> T.doIfNotNull(tProvider: (T) -> Unit): T? {
    if (this != null)
        tProvider.invoke(this)
    return this
}

fun <T : Any> T.self(tProvider: (T) -> Unit): T {
    tProvider.invoke(this)
    return this
}

