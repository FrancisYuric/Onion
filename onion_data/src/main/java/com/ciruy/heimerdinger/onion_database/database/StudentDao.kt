package com.ciruy.heimerdinger.onion_database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * 对于增删改查的相关方法的拓展
 */
@Dao
interface StudentDao:IDao<Student> {
    //varage 可变参数
    //void a(Student... stus vararg student:Student
    @Insert
    override fun insertInTx(vararg student: Student)

    @Update
    override fun updateInTx(vararg students: Student)

    @Query("DELETE FROM student")
    override fun deleteAll()

    @Query("SELECT * FROM student ORDER BY ID DESC")
    override fun queryAll():List<Student>
}