package com.example.learnkt.modules.example

import ciruy.b.heimerdinger.annotation.MVP

@MVP(model = Nothing::class,
        view = Nothing::class,
        presenter = Nothing::class)
interface IExample {
    fun method1()
    fun method2()
    fun method3()
}