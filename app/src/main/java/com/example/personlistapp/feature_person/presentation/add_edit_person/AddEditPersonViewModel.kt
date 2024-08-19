package com.example.personlistapp.feature_person.presentation.add_edit_person

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personlistapp.feature_person.domain.model.InvalidPersonException
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.domain.use_case.PersonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditPersonViewModel @Inject constructor(
    private val personUseCases: PersonUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _personName = mutableStateOf(PersonTextFieldState(
        hint = "Enter name..."
    ))
    val personName: State<PersonTextFieldState> = _personName

    private val _personCpf = mutableStateOf(PersonTextFieldState(
        hint = "Enter CPF..."
    ))
    val personCpf: State<PersonTextFieldState> = _personCpf

    private val _personColor = mutableIntStateOf(Person.personColors.random().toArgb())
    val personColor: State<Int> = _personColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPersonId: Int? = null

    init {
        savedStateHandle.get<Int>("personId")?.let { personId ->
            if (personId != -1) {
                viewModelScope.launch {
                    personUseCases.getPerson(personId)?.also { person ->
                        currentPersonId = person.id
                        _personName.value = personName.value.copy(
                            text = person.name,
                            isHintVisible = false
                        )
                        _personCpf.value = personCpf.value.copy(
                            text = person.cpf,
                            isHintVisible = false
                        )
                        _personColor.intValue = personColor.value
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditPersonEvent) {
        when(event) {
            is AddEditPersonEvent.EnteredName -> {
                _personName.value = personName.value.copy(
                    text = event.value
                )
            }
            is AddEditPersonEvent.ChangeNameFocus -> {
                _personName.value = personName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            personName.value.text.isBlank()
                )
            }
            is AddEditPersonEvent.EnteredCpf -> {
                _personCpf.value = personCpf.value.copy(
                    text = event.value
                )
            }
            is AddEditPersonEvent.ChangeCpfFocus -> {
                _personCpf.value = personCpf.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            personCpf.value.text.isBlank()
                )
            }
            is AddEditPersonEvent.ChangeColor -> {
                _personColor.intValue = event.color
            }
            is AddEditPersonEvent.SavePerson -> {
                viewModelScope.launch {
                    try {
                        personUseCases.addPerson(
                            Person(
                                name = personName.value.text,
                                cpf = personCpf.value.text,
                                dateOfBirth = System.currentTimeMillis(),
                                color = personColor.value,
                                id = currentPersonId
                            )
                        )
                        _eventFlow.emit(UiEvent.SavePerson)
                    } catch (e: InvalidPersonException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save person"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        data object SavePerson: UiEvent()
    }

}