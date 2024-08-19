package com.example.personlistapp.feature_person.presentation.persons

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.use_case.PersonUseCases
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val personUseCases: PersonUseCases
) : ViewModel() {

    private val _state = mutableStateOf(PersonsState())
    val state: State<PersonsState> = _state

    private var recentlyDeletedPerson: Person? = null

    private var getPersonsJob: Job? = null

    init {
        getPersons(PersonOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: PersonsEvent) {
        when (event) {
            is PersonsEvent.Order -> {
                if (state.value.personOrder::class == event.personOrder::class &&
                    state.value.personOrder.orderType == event.personOrder.orderType
                ) {
                    return
                }
            }

            is PersonsEvent.DeletePerson -> {
                viewModelScope.launch {
                    personUseCases.deletePerson(event.person)
                    recentlyDeletedPerson = event.person
                }
            }

            is PersonsEvent.RestorePerson -> {
                viewModelScope.launch {
                    personUseCases.addPerson(recentlyDeletedPerson ?: return@launch)
                    recentlyDeletedPerson = null
                }
            }

            is PersonsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getPersons(personOrder: PersonOrder) {
        getPersonsJob?.cancel()
        getPersonsJob = personUseCases.getPersons(personOrder)
            .onEach { persons ->
                _state.value = state.value.copy(
                    persons = persons,
                    personOrder = personOrder
                )
            }
            .launchIn(viewModelScope)
    }
}