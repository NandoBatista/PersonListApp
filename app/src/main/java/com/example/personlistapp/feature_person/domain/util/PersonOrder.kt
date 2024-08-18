package com.example.personlistapp.feature_person.domain.util

sealed class PersonOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): PersonOrder(orderType)
    class Date(orderType: OrderType): PersonOrder(orderType)
    class Color(orderType: OrderType): PersonOrder(orderType)
}