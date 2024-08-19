package com.example.personlistapp.feature_person.domain.use_case

data class PersonUseCases(
    val getPersons: GetPersonsUseCase,
    val deletePerson: DeletePersonUseCase,
    val addPerson: AddPersonUseCase,
    val getPerson: GetPersonUseCase
)