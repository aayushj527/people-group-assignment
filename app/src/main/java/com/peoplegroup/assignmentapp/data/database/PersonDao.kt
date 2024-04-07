package com.peoplegroup.assignmentapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(persons: List<PersonEntity>)

    @Query("SELECT * FROM personentity")
    fun getAllPersons(): Flow<List<PersonEntity>>
}