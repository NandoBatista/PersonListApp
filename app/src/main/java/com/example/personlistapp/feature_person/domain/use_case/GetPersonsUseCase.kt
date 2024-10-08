package com.example.personlistapp.feature_person.domain.use_case

import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.repository.PersonRepository
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonsUseCase(
    private val repository: PersonRepository
) {

    operator fun invoke(
        personOrder: PersonOrder = PersonOrder.Age(OrderType.Descending)
    ): Flow<List<Person>> {
        return repository.getPersons().map { persons ->
            when (personOrder.orderType) {
                is OrderType.Ascending -> {
                    when (personOrder) {
                        is PersonOrder.Name -> persons.sortedBy { it.name.lowercase() }
                        is PersonOrder.Age -> persons.sortedBy { it.age.toIntOrNull() ?: Int.MAX_VALUE }
                    }
                }
                is OrderType.Descending -> {
                    when (personOrder) {
                        is PersonOrder.Name -> persons.sortedByDescending { it.name.lowercase() }
                        is PersonOrder.Age -> persons.sortedByDescending { it.age.toIntOrNull() ?: Int.MAX_VALUE }
                    }
                }
            }
        }
    }
}