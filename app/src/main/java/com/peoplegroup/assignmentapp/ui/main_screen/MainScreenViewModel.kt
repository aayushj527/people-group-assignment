package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.peoplegroup.assignmentapp.data.api.PersonService
import com.peoplegroup.assignmentapp.data.database.PersonDao
import com.peoplegroup.assignmentapp.data.database.PersonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val personService: PersonService,
    private val personDao: PersonDao
) : ViewModel() {
    init {
        getPersonDataFromServer()
    }

    val persons = personDao.getAllPersons().asLiveData()

    private fun getPersonDataFromServer() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                personService.getAllPersons()
            }

            when {
                response.isSuccessful -> {
                    response.body()?.results?.let { responseList ->
                        val listToSave = responseList.map {
                            PersonEntity(person = it)
                        }

                        personDao.insertUser(listToSave)
                    }
                }

                else -> {

                }
            }
        }
    }
}