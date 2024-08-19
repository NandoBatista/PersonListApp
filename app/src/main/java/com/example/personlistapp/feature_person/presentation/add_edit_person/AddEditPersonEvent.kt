package com.example.personlistapp.feature_person.presentation.add_edit_person

import androidx.compose.ui.focus.FocusState

sealed class AddEditPersonEvent {
    data class EnteredName(val value: String): AddEditPersonEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditPersonEvent()
    data class EnteredCpf(val value: String): AddEditPersonEvent()
    data class ChangeCpfFocus(val focusState: FocusState): AddEditPersonEvent()
    data class ChangeColor(val color: Int): AddEditPersonEvent()
    data object SavePerson: AddEditPersonEvent()
}