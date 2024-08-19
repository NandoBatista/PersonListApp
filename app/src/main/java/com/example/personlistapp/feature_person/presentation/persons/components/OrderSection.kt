package com.example.personlistapp.feature_person.presentation.persons.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    personOrder: PersonOrder = PersonOrder.Date(OrderType.Descending),
    onOrderChange: (PersonOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = personOrder is PersonOrder.Name,
                onSelect = { onOrderChange(PersonOrder.Name(personOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Age",
                selected = personOrder is PersonOrder.Age,
                onSelect = { onOrderChange(PersonOrder.Age(personOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = personOrder is PersonOrder.Date,
                onSelect = { onOrderChange(PersonOrder.Date(personOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Color",
                selected = personOrder is PersonOrder.Color,
                onSelect = { onOrderChange(PersonOrder.Color(personOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = personOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(personOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = personOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(personOrder.copy(OrderType.Descending)) }
            )
        }
    }


}