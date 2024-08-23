package com.example.personlistapp.feature_person.presentation.persons

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.use_case.PersonUseCases
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder
import com.example.personlistapp.feature_person.domain.util.PersonValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsListViewModel @Inject constructor(
    private val personUseCases: PersonUseCases
) : ViewModel() {

    private val _state = mutableStateOf(PersonsListState())
    val state: State<PersonsListState> = _state

    private var recentlyDeletedPerson: Person? = null

    var newPerson: Person? by mutableStateOf(null)
        private set

    private var getPersonsJob: Job? = null


    init {
        getPersons(PersonOrder.Age(OrderType.Descending))
    }

    fun onEvent(event: PersonsListEvent) {
        when (event) {
            is PersonsListEvent.Order -> {
                if (state.value.personOrder::class == event.personOrder::class &&
                    state.value.personOrder.orderType == event.personOrder.orderType
                ) {
                    return
                }
                _state.value = _state.value.copy(personOrder = event.personOrder)
            }

            is PersonsListEvent.DeletePerson -> {
                viewModelScope.launch {
                    personUseCases.deletePerson(event.person)
                    recentlyDeletedPerson = event.person
                }
            }

            is PersonsListEvent.RestorePerson -> {
                viewModelScope.launch {
                    personUseCases.addPerson(recentlyDeletedPerson ?: return@launch)
                    recentlyDeletedPerson = null
                }
            }

            is PersonsListEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            PersonsListEvent.DismissPerson -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        isAddEditPersonSheetOpen = false,
                        nameError = null,
                        ageError = null,
                        cpfError = null,
                        cityError = null
                    )
                    delay(300L) // Animation delay
                    newPerson = null
                    _state.value = state.value.copy(
                        selectedPerson = null
                    )
                }
            }

            PersonsListEvent.OnAddNewPersonClick -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        isAddEditPersonSheetOpen = true
                    )
                    newPerson = Person(
                        id = null,
                        name = "",
                        age = "",
                        dateOfBirth = "",
                        photoBytes = null,
                        cpf = "",
                        city = "",
                        enabled = 0
                    )
                }
            }


            is PersonsListEvent.OnAgeChanged -> {
                newPerson = newPerson?.copy(
                    age = event.value
                )
            }
            is PersonsListEvent.OnCityChanged -> {
                newPerson = newPerson?.copy(
                    city = event.value
                )
            }
            is PersonsListEvent.OnCpfChanged -> {
                newPerson = newPerson?.copy(
                    cpf = event.value
                )
            }
            is PersonsListEvent.OnNameChanged -> {
                newPerson = newPerson?.copy(
                    name = event.value
                )
            }

            is PersonsListEvent.OnPhotoPicked -> {
                newPerson = newPerson?.copy(
                    photoBytes = event.bytes
                )
            }
            PersonsListEvent.SavePerson -> {
                newPerson?.let { person ->
                    val result = PersonValidator.validateContact(person)
                    val errors = listOfNotNull(
                        result.nameError,
                        result.ageError,
                        result.cpfError,
                        result.cityError
                    )

                    if(errors.isEmpty()) {
                        _state.value = state.value.copy(
                            isAddEditPersonSheetOpen = false,
                            nameError = null,
                            ageError = null,
                            cpfError = null,
                            cityError = null
                        )
                        viewModelScope.launch {
                            personUseCases.addPerson(person)
                            delay(300L) // Animation delay
                            newPerson = null
                        }
                    } else {
                        _state.value = state.value.copy(
                            nameError = result.nameError,
                            ageError = result.ageError,
                            cpfError = result.cpfError,
                            cityError = result.cityError
                        )
                    }
                }
            }
            is PersonsListEvent.SelectPerson -> {
                _state.value = state.value.copy(
                    selectedPerson = event.person,
                    isAddEditPersonSheetOpen = true
                )
                newPerson = event.person
            }
            else -> Unit
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