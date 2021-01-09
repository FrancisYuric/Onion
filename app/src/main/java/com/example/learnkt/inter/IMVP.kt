package com.example.learnkt.inter

/**
 * 堪称完美的MVP结构
 */
interface IPresenter<
        M : IModel<M, V, P>,
        V : IView<M, V, P>,
        P : IPresenter<M, V, P>>

interface IModel<
        M : IModel<M, V, P>,
        V : IView<M, V, P>,
        P : IPresenter<M, V, P>>

interface IView<
        M : IModel<M, V, P>,
        V : IView<M, V, P>,
        P : IPresenter<M, V, P>>
