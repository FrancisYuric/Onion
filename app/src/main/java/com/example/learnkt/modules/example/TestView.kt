package com.example.learnkt.modules.example

import com.example.learnkt.inter.IView

interface TestView : IView<TestModel, TestView, TestPresenter> {
    override fun presenter() = TestPresenter
}