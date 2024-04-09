package com.peoplegroup.assignmentapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PersonEntity::class], version = 1)
@TypeConverters(PersonTypeConverter::class, StatusTypeConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}