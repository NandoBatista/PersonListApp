package com.example.personlistapp.feature_person.domain.use_case

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.repository.PersonRepository

class AddPersonUseCase(
    private val repository: PersonRepository
) {

    suspend operator fun invoke(person: Person) {
        repository.insertPerson(person)
    }
}