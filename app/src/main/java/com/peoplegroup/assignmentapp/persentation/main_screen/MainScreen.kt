package com.peoplegroup.assignmentapp.persentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.R
import com.peoplegroup.assignmentapp.domain.model.PersonInfo
import com.peoplegroup.assignmentapp.domain.model.Status
import com.peoplegroup.assignmentapp.utilities.PersonCardView
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var personList by remember { mutableStateOf(listOf<PersonInfo>()) }
    
    LaunchedEffect(key1 = Unit) {
        AppClass.connectivityState.observe(lifecycleOwner) {
            if (mainScreenViewModel.shouldRetry(it)) {
                mainScreenViewModel.getPersonData()
            }
        }

        mainScreenViewModel.persons.observe(lifecycleOwner) { personEntityList ->
            personList = personEntityList
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = stringResource(id = R.string.profile_matches),
            style = TextStyle.Default.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = personList) { item ->
                PersonCardView(
                    person = item,
                    accepted = {
                        mainScreenViewModel.viewModelScope.launch {
                            mainScreenViewModel.personRepository.updatePersonStatus(
                                Status.Accepted, it
                            )
                        }
                    },
                    declined = {
                        mainScreenViewModel.viewModelScope.launch {
                            mainScreenViewModel.personRepository.updatePersonStatus(
                                Status.Declined, it
                            )
                        }
                    }
                )
            }

            if (personList.isNotEmpty()) {
                item {
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = {
                            mainScreenViewModel.getPersonData()
                        }
                    ) {
                        if (mainScreenViewModel.state.loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp,
                                color = Color.White
                            )
                        } else {
                            Text(text = stringResource(R.string.load_more))
                        }
                    }
                }
            }
        }
    }
}