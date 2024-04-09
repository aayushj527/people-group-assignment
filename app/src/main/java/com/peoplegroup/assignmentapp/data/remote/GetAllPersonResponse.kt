package com.peoplegroup.assignmentapp.data.remote

import com.peoplegroup.assignmentapp.domain.model.Person

data class GetAllPersonResponse(
    val results: List<Person>
)
