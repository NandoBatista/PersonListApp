package com.example.personlistapp.feature_person.domain.util

sealed class PersonOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): PersonOrder(orderType)
    class Age(orderType: OrderType): PersonOrder(orderType)

    fun copy(orderType: OrderType): PersonOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Age -> Age(orderType)
        }
    }
}