package com.peoplegroup.assignmentapp.ui.main_screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.R
import com.peoplegroup.assignmentapp.utilities.Person
import com.peoplegroup.assignmentapp.utilities.PersonCardView

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var personList by remember { mutableStateOf(listOf<Person>()) }

    AppClass.connectivityState.observe(lifecycleOwner) {
        if (mainScreenViewModel.shouldRetry(it)) {
            mainScreenViewModel.getPersonDataFromServer()
        }
    }

    mainScreenViewModel.persons.observe(lifecycleOwner) { personEntityList ->
        personList = personEntityList.map {
            it.person
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = context.getString(R.string.profile_matches),
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
                PersonCardView(person = item)
            }

            if (personList.isNotEmpty()) {
                item {
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = {
                            mainScreenViewModel.getPersonDataFromServer()
                        }
                    ) {
                        if (mainScreenViewModel.apiCalling) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp,
                                color = Color.White
                            )
                        } else {
                            Text(text = context.getString(R.string.load_more))
                        }
                    }
                }
            }
        }
    }
}