package com.example.learnkt.database

import androidx.room.Database

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase {
    abstract fun getStudentDao(): StudentDao
}