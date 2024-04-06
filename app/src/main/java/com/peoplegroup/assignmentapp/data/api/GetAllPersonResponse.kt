package com.peoplegroup.assignmentapp.data.api

import com.peoplegroup.assignmentapp.data.database.Person

data class GetAllPersonResponse(
    val results: List<Person>
)
