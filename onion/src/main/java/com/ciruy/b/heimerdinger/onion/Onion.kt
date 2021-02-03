package com.ciruy.b.heimerdinger.onion

abstract class Onion<T> {
    abstract fun isSuspend():Boolean
    abstract fun consume():T
    companion object {
        fun <F, T> sus(fOnion:Onion<F>,
                       f2OnionT:(F)->Onion<T>): Suspend<F, T> = Suspend(fOnion, f2OnionT)
        fun <T> ret(v2t: () -> T): Ret<T> = Ret(v2t)
    }

    class Suspend<F, T>(val onion:Onion<F>,
                        val f2OnionT:(F)->Onion<T>) : Onion<T>() {
        override fun consume(): T = f2OnionT.invoke(onion.consume()).consume()
        override fun isSuspend(): Boolean = true
    }

    class Ret<T>(val v2t: () -> T) : Onion<T>() {
        override fun consume(): T = v2t.invoke()
        override fun isSuspend(): Boolean = false
    }
}