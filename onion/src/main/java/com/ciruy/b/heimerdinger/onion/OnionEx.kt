package com.ciruy.b.heimerdinger.onion


fun <F, T, P> F.onion(pairConsumer: (Pair<F, T>) -> P): (() -> (T)) -> P = {
    pairConsumer.invoke(Pair(this, it.invoke()))
}

/**
 * Activity.addDisposable(disposable):(()->disposable)
 * (()->disposable)
 */



