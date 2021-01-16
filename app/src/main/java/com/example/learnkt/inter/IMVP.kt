package com.example.learnkt.inter

/**
 * 堪称完美的MVP结构
 */
abstract class IPresenter<
        M : IModel<M>,
        P : IPresenter<M, P>> {
    abstract fun model(): M
    val model by lazy { model() }
}

interface IModel<M : IModel<M>> {
//    fun callback(resultListener: ResultListener<M>) = object : ResultListener<M> {
//        override fun success(t: M) {
//            resultListener.success(t)
//        }
//
//        override fun failure(errMes: String?) {
//            resultListener.failure(errMes)
//        }
//    }
}

interface IView<
        M : IModel<M>,
        V : IView<M, V, P>,
        P : IPresenter<M, P>> {
    fun presenter(): P
}

