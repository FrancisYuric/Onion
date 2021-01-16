package com.ciruy.heimerdinger.onion_database.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    @ColumnInfo(name = "name")
    lateinit var name:String

    @ColumnInfo(name = "age")
    var age:Int = 0

    constructor(name:String, age:Int):this() {
        this.name = name
        this.age = age
    }
}