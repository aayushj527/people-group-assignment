package com.peoplegroup.assignmentapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PersonEntity::class], version = 1)
@TypeConverters(PersonTypeConverter::class)
abstract class DataBase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}