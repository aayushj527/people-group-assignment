package com.peoplegroup.assignmentapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {

    @GET("api/")
    suspend fun getAllPersons(
        @Query("results") results: Int
    ): Response<GetAllPersonResponse>
}