package com.peoplegroup.assignmentapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peoplegroup.assignmentapp.domain.model.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(persons: List<PersonEntity>)

    @Query("SELECT * FROM personentity")
    fun getAllPersons(): Flow<List<PersonEntity>>

    @Query("UPDATE personentity SET status=:status WHERE id = :id")
    suspend fun updatePersonStatus(status: Status, id: Int)
}