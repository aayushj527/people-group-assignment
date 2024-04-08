package com.peoplegroup.assignmentapp.data.api

import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.utilities.ConnectionState
import retrofit2.Response
import javax.inject.Inject

class PersonDomain @Inject constructor(private val personService: PersonService) {
    companion object {
        const val PERSON_DEFAULT_LIMIT = 2
    }

    suspend fun getAllPersons(results: Int = PERSON_DEFAULT_LIMIT): Response<GetAllPersonResponse>? {
        return if (AppClass.connectivityState.value == ConnectionState.Available) {
            personService.getAllPersons(results)
        } else {
            null
        }
    }
}