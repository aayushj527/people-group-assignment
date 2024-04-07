package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peoplegroup.assignmentapp.Person

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    persons: List<Person>
) {
//    val persons: MutableLiveData<List<Person>> by mainScreenViewModel.personDao.getAllPersons().collectAsState(initial =)

    Column(modifier = Modifier.fillMaxSize()) {
//        Text(
//            text = "Profile Matches",
//            style = TextStyle.Default.copy(
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold
//            )
//        )

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {

            stickyHeader {
                Text(
                    text = "Profile Matches",
                    style = TextStyle.Default.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            items(items = persons) {

            }
        }
    }
}