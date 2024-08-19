package com.example.personlistapp.feature_person.presentation.util

sealed class Screen(val route: String) {
    data object PersonsScreen : Screen("persons_screen")
    data object AddEditPersonScreen : Screen("add_edit_person_screen")
}