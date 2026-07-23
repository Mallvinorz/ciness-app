package com.example.cinessapp.data.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface Dao {
    @Insert
    fun insertContent(vararg content: Conten)
}