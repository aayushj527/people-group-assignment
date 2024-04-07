package com.peoplegroup.assignmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import com.peoplegroup.assignmentapp.data.database.PersonDao
import com.peoplegroup.assignmentapp.ui.main_screen.MainScreen
import com.peoplegroup.assignmentapp.ui.theme.PeopleGroupAssignmentAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var personDao: PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var persons: List<Person> = listOf()

        personDao
            .getAllPersons()
            .flowWithLifecycle(lifecycle)
            .onEach { entries ->
                persons = entries.map { entry ->
                    entry.person
                }
            }

        setContent {
            PeopleGroupAssignmentAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        mainScreenViewModel = hiltViewModel(),
                        persons = persons
                    )
                }
            }
        }
    }
}