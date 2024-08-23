package com.example.personlistapp.feature_person.presentation.persons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.personlistapp.R
import com.example.personlistapp.core.presentation.BottomSheetFromWish
import com.example.personlistapp.feature_person.domain.model.Person
import com.example.personlistapp.feature_person.presentation.persons.PersonsListEvent
import com.example.personlistapp.feature_person.presentation.persons.PersonsListState

@Composable
fun AddEditPersonSheet(
    state: PersonsListState,
    newPerson: Person?,
    isOpen: Boolean,
    onEvent: (PersonsListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetFromWish(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                if (newPerson?.photoBytes == null) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .clickable {
                                onEvent(PersonsListEvent.OnAddPhotoClicked)
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                shape = RoundedCornerShape(40)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    PersonPhoto(
                        person = newPerson,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(PersonsListEvent.OnAddPhotoClicked)
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                PersonTextField(
                    value = newPerson?.name ?: "",
                    placeholder = stringResource(R.string.person_txt_name),
                    error = state.nameError,
                    onValueChanged = {
                        onEvent(PersonsListEvent.OnNameChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                PersonTextField(
                    value = newPerson?.age ?: "",
                    placeholder = stringResource(R.string.person_txt_age),
                    error = state.ageError,
                    onValueChanged = {
                        onEvent(PersonsListEvent.OnAgeChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isNumeric = true
                )
                Spacer(Modifier.height(16.dp))
                PersonTextField(
                    value = newPerson?.cpf ?: "",
                    placeholder = stringResource(R.string.person_txt_cpf),
                    error = state.cpfError,
                    onValueChanged = {
                        onEvent(PersonsListEvent.OnCpfChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isNumeric = true
                )
                Spacer(Modifier.height(16.dp))
                PersonTextField(
                    value = newPerson?.city ?: "",
                    placeholder = stringResource(R.string.person_txt_city),
                    error = state.cityError,
                    onValueChanged = {
                        onEvent(PersonsListEvent.OnCityChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onEvent(PersonsListEvent.SavePerson)
                    }
                ) {
                    Text(text = stringResource(R.string.person_txt_save))
                }
            }
            IconButton(
                onClick = {
                    onEvent(PersonsListEvent.DismissPerson)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Composable
private fun PersonTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isNumeric: Boolean = false
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (isNumeric) KeyboardType.Number else KeyboardType.Text
            ),
        )
        if(error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}