package com.ciruy.heimerdinger.onion_database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * 对于增删改查的相关方法的拓展
 */
@Dao
interface StudentDao {
    //varage 可变参数
    //void a(Student... stus vararg student:Student
    @Insert
    fun insertStudents(vararg student: Student)


    @Update
    fun updateStudents(vararg students: Student)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

    @Query("SELECT * FROM student ORDER BY ID DESC")
    fun queryAllStudent():List<Student>
}