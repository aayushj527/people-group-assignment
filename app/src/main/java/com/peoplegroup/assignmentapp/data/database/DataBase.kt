package com.peoplegroup.assignmentapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}