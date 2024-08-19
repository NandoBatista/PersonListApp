package com.example.personlistapp.feature_person.presentation.persons

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.util.PersonOrder

sealed class PersonsEvent {
    data class Order(val personOrder: PersonOrder): PersonsEvent()
    data class DeletePerson(val person: Person): PersonsEvent()
    data object RestorePerson: PersonsEvent()
    data object ToggleOrderSection: PersonsEvent()
}