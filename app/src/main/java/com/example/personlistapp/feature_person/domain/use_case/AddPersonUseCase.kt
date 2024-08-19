package com.example.personlistapp.feature_person.domain.use_case

import com.example.personlistapp.feature_person.domain.model.InvalidPersonException
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.repository.PersonRepository
import com.example.personlistapp.utils.isCPFValid

class AddPersonUseCase(
    private val repository: PersonRepository
) {

    @Throws(InvalidPersonException::class)
    suspend operator fun invoke(person: Person) {
        if(person.name.isBlank()) {
            throw InvalidPersonException("The name of the person can't be empty.")
        }
        if(!person.cpf.isCPFValid()) {
            throw InvalidPersonException("The provided CPF '${person.cpf}' is invalid. Please enter a valid CPF.")
        }

        repository.insertPerson(person)
    }
}