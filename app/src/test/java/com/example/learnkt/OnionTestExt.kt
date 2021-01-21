package com.example.learnkt

import org.junit.Assert.assertEquals

fun <T> T.isTrue() = assertEquals(true, this)
fun <T> T.isFalse() = assertEquals(false, this)

fun <F,T>((F)->T).check(f:F,t:T) = run {
    assertEquals(t, this.invoke(f))
}