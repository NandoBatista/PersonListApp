package com.example.personlistapp.feature_person.presentation.persons

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder

data class PersonsListState(
    val persons: List<Person> = emptyList(),
    var personOrder: PersonOrder = PersonOrder.Age(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val selectedPerson: Person? = null,
    val isAddEditPersonSheetOpen: Boolean = false,
    val nameError: String? = null,
    val ageError: String? = null,
    val cpfError: String? = null,
    val cityError: String? = null
)
