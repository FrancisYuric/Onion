package com.example.learnkt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun getStudentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDatabase? = null
        fun getDatabase(context: Context): StudentDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, StudentDatabase::class.java, "student_database.db")
                        .allowMainThreadQueries()//允许主线程进行数据查询操作
                        .build()
            }
            return INSTANCE
        }
        //忽略初始化Context操作
        fun getDatabase():StudentDatabase? = INSTANCE
    }
}