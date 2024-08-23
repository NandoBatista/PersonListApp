package com.example.personlistapp.feature_person.presentation.persons.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.personlistapp.R
import com.example.personlistapp.feature_person.domain.util.OrderType
import com.example.personlistapp.feature_person.domain.util.PersonOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    personOrder: PersonOrder = PersonOrder.Age(OrderType.Descending),
    onOrderChange: (PersonOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.person_txt_name),
                selected = personOrder is PersonOrder.Name,
                onSelect = { onOrderChange(PersonOrder.Name(personOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.person_txt_age),
                selected = personOrder is PersonOrder.Age,
                onSelect = { onOrderChange(PersonOrder.Age(personOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.person_txt_ascending),
                selected = personOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(personOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.person_txt_descending),
                selected = personOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(personOrder.copy(OrderType.Descending)) }
            )
        }
    }


}