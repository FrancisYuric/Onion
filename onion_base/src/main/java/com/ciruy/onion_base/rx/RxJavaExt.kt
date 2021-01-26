package com.ciruy.onion_base.rx

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import java.io.Serializable

fun <F : Serializable, T> reFuncAfterDefaultFunc(valueCheck: (F) -> Boolean,
                                                 defaultFunc: Flowable<T>): FlowableTransformer<F, F> {
    return FlowableTransformer { flowableFrom ->
        flowableFrom.flatMap { from ->
            if (valueCheck.invoke(from)) {
                return@flatMap defaultFunc.flatMap { flowableFrom }
            } else Flowable.just(from)
        }
    }
}