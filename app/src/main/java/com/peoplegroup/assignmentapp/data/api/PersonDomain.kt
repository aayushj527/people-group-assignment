package com.peoplegroup.assignmentapp.data.api

import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.R
import com.peoplegroup.assignmentapp.utilities.ConnectionState
import com.peoplegroup.assignmentapp.utilities.getCurrentConnectivityState
import com.peoplegroup.assignmentapp.utilities.showToast
import retrofit2.Response
import javax.inject.Inject

class PersonDomain @Inject constructor(private val personService: PersonService) {
    companion object {
        const val PERSON_DEFAULT_LIMIT = 10
    }

    suspend fun getAllPersons(results: Int = PERSON_DEFAULT_LIMIT): Response<GetAllPersonResponse>? {
        return if (getCurrentConnectivityState() == ConnectionState.Available) {
            personService.getAllPersons(results)
        } else {
            showToast(AppClass.getContext().getString(R.string.error_network_disconnected))
            null
        }
    }
}