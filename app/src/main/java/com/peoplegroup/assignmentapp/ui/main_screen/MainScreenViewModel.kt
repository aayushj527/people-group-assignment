package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.lifecycle.ViewModel
import com.peoplegroup.assignmentapp.data.api.PersonService
import com.peoplegroup.assignmentapp.data.database.PersonDao
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    personService: PersonService,
    personDao: PersonDao
) : ViewModel() {
}