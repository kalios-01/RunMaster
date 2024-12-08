package com.kaliostech.runmaster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kaliostech.runmaster.data.dao.RunDAO
import com.kaliostech.runmaster.data.entities.Run
import com.kaliostech.runmaster.util.Converters

@Database(
    entities = [Run::class], version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase:RoomDatabase() {
    abstract fun getRunDao():RunDAO
}