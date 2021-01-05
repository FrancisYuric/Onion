package com.example.learnkt.test

class Student(id: Int) : Person(id) {
    //在kotlin不存在默认值，为了避免null默认值的出现
    lateinit var name: String
    //基本类型不支持懒加载
//    lateinit var age:Int

}