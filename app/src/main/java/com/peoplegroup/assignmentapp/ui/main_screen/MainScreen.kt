package com.peoplegroup.assignmentapp.ui.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current

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
            mainScreenViewModel.persons.observe(lifecycleOwner) { personList ->
                items(items = personList) {
                    Text(
                        text = it.person.name?.first.toString(),
                        style = TextStyle.Default.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}