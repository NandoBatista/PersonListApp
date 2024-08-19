package com.example.personlistapp.feature_person.data.repository

import com.example.personlistapp.feature_person.data.data_source.PersonDao
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val dao: PersonDao
) : PersonRepository {

    override fun getPersons(): Flow<List<Person>> {
        return dao.getPersons()
    }

    override suspend fun getPersonById(id: Int): Person? {
        return dao.getPersonById(id)
    }

    override suspend fun insertPerson(person: Person) {
        dao.insertPerson(person)
    }

    override suspend fun deletePerson(person: Person) {
        dao.deletePerson(person)
    }

}