package com.peoplegroup.assignmentapp.data.api

import com.peoplegroup.assignmentapp.data.database.Person
import retrofit2.Response
import retrofit2.http.GET

interface PersonService {
    @GET()
    suspend fun getAllPersons(): Response<GetAllPersonResponse>
}