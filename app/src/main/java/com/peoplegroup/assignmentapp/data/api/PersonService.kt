package com.peoplegroup.assignmentapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {
    companion object {
        const val PERSON_DEFAULT_LIMIT = 10
    }

    @GET("api/")
    suspend fun getAllPersons(
        @Query("results") results: Int = PERSON_DEFAULT_LIMIT
    ): Response<GetAllPersonResponse>
}