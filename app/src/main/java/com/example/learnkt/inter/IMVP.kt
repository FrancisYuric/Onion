package com.example.learnkt.inter

interface IPresenter<M:IModel,V:IView>:ResultListener<M>{

}
interface IModel {
}
interface IView{

}
