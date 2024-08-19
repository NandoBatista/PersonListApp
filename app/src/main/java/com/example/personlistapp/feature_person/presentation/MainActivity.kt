package com.example.personlistapp.feature_person.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.personlistapp.feature_person.presentation.add_edit_person.AddEditPersonScreen
import com.example.personlistapp.feature_person.presentation.persons.PersonsScreen
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
                        startDestination = Screen.PersonsScreen.route
                    ) {
                        composable(route = Screen.PersonsScreen.route) {
                            PersonsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditPersonScreen.route +
                                    "?personId={personId}&personColor={personColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "personId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "personColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("personColor") ?: -1
                            AddEditPersonScreen(
                                navController = navController,
                                personColor = color
                            )
                        }

                    }

                }

            }
        }
    }
}