package com.example.personlistapp.feature_person.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personlistapp.core.presentation.ImagePickerFactory
import com.example.personlistapp.feature_person.presentation.persons.PersonsListScreen
import com.example.personlistapp.feature_person.presentation.util.Screen
import com.example.personlistapp.ui.theme.PersonListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonListAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PersonsListScreen.route
                    ) {
                        composable(route = Screen.PersonsListScreen.route) {
                            PersonsListScreen(
                                imagePicker = ImagePickerFactory().createPicker()
                            )
                        }
                    }
                }
            }
        }
    }
}