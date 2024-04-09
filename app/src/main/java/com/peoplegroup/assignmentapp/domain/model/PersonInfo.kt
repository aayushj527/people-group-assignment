package com.peoplegroup.assignmentapp.domain.model

data class PersonInfo(
    val id: Int,
    val name: String? = null,
    val imageUrl: String? = null,
    val address: String? = null,
    val status: Status? = null
)
