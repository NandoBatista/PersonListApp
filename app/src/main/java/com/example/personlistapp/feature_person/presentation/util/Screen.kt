package com.example.personlistapp.feature_person.presentation.util

sealed class Screen(val route: String) {
    data object PersonsListScreen : Screen("persons_list_screen")
}