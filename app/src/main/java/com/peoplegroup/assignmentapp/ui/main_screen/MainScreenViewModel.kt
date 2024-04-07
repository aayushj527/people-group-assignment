package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.R
import com.peoplegroup.assignmentapp.data.api.PersonDomain
import com.peoplegroup.assignmentapp.data.database.PersonDao
import com.peoplegroup.assignmentapp.data.database.PersonEntity
import com.peoplegroup.assignmentapp.utilities.ConnectionState
import com.peoplegroup.assignmentapp.utilities.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val personDomain: PersonDomain,
    private val personDao: PersonDao
) : ViewModel() {
    init {
        getPersonDataFromServer()
    }

    val persons = personDao.getAllPersons().asLiveData()
    private var dataSyncedFromApi = false
    private var dataSyncingJob: Job? = null

    fun getPersonDataFromServer() {
        dataSyncingJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                personDomain.getAllPersons()
            }?.let { response ->
                when {
                    response.isSuccessful -> {
                        response.body()?.results?.let { responseList ->
                            val listToSave = responseList.map {
                                PersonEntity(person = it)
                            }

                            dataSyncedFromApi = true
                            personDao.insertUser(listToSave)
                        }
                    }

                    else -> {
                        showToast(AppClass.getContext().getString(R.string.error_occurred))
                    }
                }
            }
        }
    }

    fun shouldRetry(connectionState: ConnectionState): Boolean {
        return connectionState == ConnectionState.Available &&
                !dataSyncedFromApi &&
                dataSyncingJob?.isActive == false
    }
}