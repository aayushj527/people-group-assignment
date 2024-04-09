package com.peoplegroup.assignmentapp.data.remote

import com.peoplegroup.assignmentapp.domain.model.Person

/**
 *  Response format of getAllPersons API.
 */
data class GetAllPersonResponse(
    val results: List<Person>
)
