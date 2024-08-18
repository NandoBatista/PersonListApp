package com.example.personlistapp.feature_person.domain.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}