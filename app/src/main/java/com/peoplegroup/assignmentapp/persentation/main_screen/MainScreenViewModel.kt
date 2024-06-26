package com.peoplegroup.assignmentapp.persentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.peoplegroup.assignmentapp.domain.repository.PersonRepository
import com.peoplegroup.assignmentapp.utilities.ConnectionState
import com.peoplegroup.assignmentapp.utilities.Resource
import com.peoplegroup.assignmentapp.utilities.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val personRepository: PersonRepository
) : ViewModel() {
    var state by mutableStateOf(MainScreenState())

    /**
     *  Person data live data from local DB.
     */
    val persons = personRepository.getPersonDataFromLocal().asLiveData()

    init {
        getPersonDataFromRemote()
    }

    /**
     *  To fetch data from remote server.
     */
    fun getPersonDataFromRemote() {
        viewModelScope.launch {
            personRepository
                .getPersonDataFromRemote()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(loading = result.isLoading)
                        }
                        is Resource.Success -> {
                            state = state.copy(dataSyncedFromApi = true)
                        }
                        is Resource.Error -> {
                            result.message?.let {
                                showToast(result.message)
                            }
                        }
                    }
                }
        }
    }

    /**
     *  To decide if API should be re-called or not, when network
     *  connection is restored.
     */
    fun shouldRetry(connectionState: ConnectionState): Boolean {
        return connectionState == ConnectionState.Available &&
                !state.dataSyncedFromApi &&
                !state.loading
    }
}