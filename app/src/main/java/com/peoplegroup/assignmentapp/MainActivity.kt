package com.peoplegroup.assignmentapp

import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.peoplegroup.assignmentapp.data.local.PersonDao
import com.peoplegroup.assignmentapp.persentation.main_screen.MainScreen
import com.peoplegroup.assignmentapp.ui.theme.PeopleGroupAssignmentAppTheme
import com.peoplegroup.assignmentapp.utilities.CONNECTIVITY_INTENT_ACTION
import com.peoplegroup.assignmentapp.utilities.networkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var personDao: PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                networkChangeReceiver,
                IntentFilter().apply {
                    addAction(CONNECTIVITY_INTENT_ACTION)
                },
                Context.RECEIVER_EXPORTED
            )
        } else {
            registerReceiver(
                networkChangeReceiver,
                IntentFilter().apply {
                    addAction(CONNECTIVITY_INTENT_ACTION)
                }
            )
        }

        setContent {
            PeopleGroupAssignmentAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        mainScreenViewModel = hiltViewModel()
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }
}