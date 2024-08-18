package com.example.personlistapp.feature_person.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.personlistapp.ui.theme.BabyBlue
import com.example.personlistapp.ui.theme.LightGreen
import com.example.personlistapp.ui.theme.RedOrange
import com.example.personlistapp.ui.theme.RedPink
import com.example.personlistapp.ui.theme.Violet

@Entity
data class Person(
    val name: String,
    val dateOfBirth: Long,
    val cpf: String,
    val city: String,
    val image: String,
    val color: Int,
    val enabled: Boolean,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val personColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
