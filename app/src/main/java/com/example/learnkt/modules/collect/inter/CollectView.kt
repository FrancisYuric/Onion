package com.example.learnkt.modules.collect.inter

import com.example.learnkt.inter.IView

interface CollectView :IView<CollectModel,CollectView,CollectPresenter>{
    override fun presenter(): CollectPresenter = CollectPresenter()
}