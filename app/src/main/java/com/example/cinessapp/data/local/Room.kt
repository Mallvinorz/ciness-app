package com.example.cinessapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Conten::class], version = 1)
abstract class Room : RoomDatabase() {
    abstract fun contentDao(): Dao
}

