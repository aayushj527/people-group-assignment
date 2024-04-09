package com.peoplegroup.assignmentapp.persentation.main_screen

/**
 *  @param loading To maintain the loading state of API calling.
 *  @param dataSyncedFromApi To track if the data is initially synced from API or not.
 */
data class MainScreenState(
    val loading: Boolean = false,
    val dataSyncedFromApi: Boolean = false
)