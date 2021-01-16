package com.example.learnkt.modules.collect.inter

import com.example.learnkt.inter.IPresenter

class CollectPresenter :IPresenter<CollectModel,CollectPresenter>(){
    override fun model(): CollectModel = CollectModel()
}