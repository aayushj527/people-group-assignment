package com.peoplegroup.assignmentapp.data.api

import retrofit2.Response
import retrofit2.http.GET

interface PersonService {
    @GET("api/?results=")
    suspend fun getAllPersons(): Response<GetAllPersonResponse>
}