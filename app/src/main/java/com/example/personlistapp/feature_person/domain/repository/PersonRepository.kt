package com.example.personlistapp.feature_person.domain.repository

import com.example.personlistapp.feature_person.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersons(): Flow<List<Person>>

    suspend fun getPersonById(id: Int): Person?

    suspend fun insertPerson(person: Person)

    suspend fun updatePerson(person: Person)

    suspend fun deletePerson(person: Person)
}