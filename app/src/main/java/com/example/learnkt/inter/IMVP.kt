package com.example.learnkt.inter

/**
 * 堪称完美的MVP结构
 */
interface IPresenter<
        M : IModel<M>,
        V : IView<M, V, P>,
        P : IPresenter<M, V, P>>{
    fun model():M
}

interface IModel<M : IModel<M>>:ResultListener<M>{

}

interface IView<
        M : IModel<M>,
        V : IView<M, V, P>,
        P : IPresenter<M, V, P>>{
    fun presenter():P
}

