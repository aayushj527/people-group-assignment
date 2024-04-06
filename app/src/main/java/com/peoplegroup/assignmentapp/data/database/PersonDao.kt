package com.peoplegroup.assignmentapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(persons: List<Person>)

    @Query("SELECT * FROM person")
    suspend fun getAllPersons(): List<Person>
}