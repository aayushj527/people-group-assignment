package com.peoplegroup.assignmentapp.domain.model

/**
 *  DTO to hold data that needs to be displayed on the UI.
 */
data class PersonInfo(
    val id: Int,
    val name: String? = null,
    val imageUrl: String? = null,
    val address: String? = null,
    val status: Status? = null
)
