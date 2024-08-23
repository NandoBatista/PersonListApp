package com.example.personlistapp.feature_person.presentation.persons

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.util.PersonOrder

sealed interface PersonsListEvent {
    data class Order(val personOrder: PersonOrder): PersonsListEvent
    data class DeletePerson(val person: Person): PersonsListEvent
    data object RestorePerson: PersonsListEvent
    data object ToggleOrderSection: PersonsListEvent
    data object SavePerson: PersonsListEvent
    data object OnAddNewPersonClick: PersonsListEvent
    data object DismissPerson: PersonsListEvent
    data class OnNameChanged(val value: String): PersonsListEvent
    data class OnAgeChanged(val value: String): PersonsListEvent
    data class OnDateOfBirthChanged(val value: String): PersonsListEvent
    data class OnCpfChanged(val value: String): PersonsListEvent
    data class OnCityChanged(val value: String): PersonsListEvent
    class OnPhotoPicked(val bytes: ByteArray): PersonsListEvent
    data object OnAddPhotoClicked: PersonsListEvent
    data class SelectPerson(val person: Person): PersonsListEvent
}