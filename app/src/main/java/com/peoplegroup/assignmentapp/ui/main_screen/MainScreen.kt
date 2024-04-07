package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.utilities.Person

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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
            text = "Profile Matches",
            style = TextStyle.Default.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(items = personList) { item ->
                PersonCardView(person = item)
            }
        }
    }
}

@Composable
fun PersonCardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        AsyncImage(
            model = person.picture?.large,
            contentDescription = ""
        )
    }
}