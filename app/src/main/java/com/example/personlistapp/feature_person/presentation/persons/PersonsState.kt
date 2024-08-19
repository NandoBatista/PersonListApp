package com.example.personlistapp.feature_person.presentation.persons

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder

data class PersonsState(
    val persons: List<Person> = emptyList(),
    val personOrder: PersonOrder = PersonOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
