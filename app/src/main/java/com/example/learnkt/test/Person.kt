package com.example.learnkt.test

open class Person(id: Int) {
    constructor(id: Int, name: String) : this(id) {

    }

    constructor(id: Int, sex: Char) : this(id) {

    }

    constructor() : this(797) {

    }
}