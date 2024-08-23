package com.example.personlistapp.feature_person.domain.util

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.utils.isCPFValid

object PersonValidator {

    fun validateContact(person: Person): ValidationResult {
        var result = ValidationResult()

        if(person.name.isBlank()) {
            result = result.copy(nameError = "The name can't be empty.")
        }

        if(person.age.isBlank()) {
            result = result.copy(ageError = "The age can't be empty.")
        }

        if(person.dateOfBirth.isBlank()) {
            result = result.copy(dateOfBirthError = "The date of birth can't be empty.")
        }

        if (!person.cpf.isCPFValid()) {
            result = result.copy(cpfError = "Invalid CPF.")
        }

        if(person.city.isBlank()) {
            result = result.copy(cityError = "The city can't be empty.")
        }

        return result
    }

    data class ValidationResult(
        val nameError: String? = null,
        val ageError: String? = null,
        val dateOfBirthError: String? = null,
        val cpfError: String? = null,
        val cityError: String? = null
    )
}