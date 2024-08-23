package com.example.personlistapp.feature_person.presentation.persons.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personlistapp.feature_person.domain.model.Person

@Composable
fun PersonPreviewItem(
    person: Person,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PersonPhoto(
            person = person,
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = person.name,
        )
    }
}