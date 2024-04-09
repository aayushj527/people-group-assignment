package com.peoplegroup.assignmentapp.domain.repository

import com.peoplegroup.assignmentapp.data.local.PersonEntity
import com.peoplegroup.assignmentapp.domain.model.PersonInfo
import com.peoplegroup.assignmentapp.domain.model.Status
import com.peoplegroup.assignmentapp.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPersonDataFromRemote(
        limit: Int? = null
    ): Flow<Resource<List<PersonInfo>>>

    fun getPersonDataFromLocal(): Flow<List<PersonInfo>>

    suspend fun insertPersonData(persons: List<PersonEntity>)

    suspend fun updatePersonStatus(status: Status, id: Int)
}