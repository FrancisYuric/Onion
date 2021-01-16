package com.ciruy.heimerdinger.onion_database.database

import androidx.room.Dao


interface IDao<T>{
    fun insertInTx(vararg t:T)
    fun updateInTx(vararg t:T)
    fun deleteAll()
    fun queryAll():List<T>
}