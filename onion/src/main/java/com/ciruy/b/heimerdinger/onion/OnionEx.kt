package com.ciruy.b.heimerdinger.onion


fun <F, T, P> F.onion(pairConsumer: (Pair<F, T>) -> P): (() -> (T)) -> P = {
    pairConsumer.invoke(Pair(this, it.invoke()))
}

fun <F,T> F.from(pairConsumer: (Pair<F, T>) -> Unit):(()->T)->Unit {
    return {
        pairConsumer.invoke(Pair(this, it.invoke()))
    }
}
/**
 * Activity.addDisposable(disposable):((()->disposable)->Unit)
 * (()->disposable)->Unit
 */
fun <F, T, P> ((() -> F) -> Unit).fromTwo(pair2f: (Pair<T, P>) -> F): (() -> T) -> (() -> P) -> Unit {
    return { t ->
        { p ->
            this.invoke { pair2f.invoke(Pair(t.invoke(), p.invoke())) }
        }
    }
}



