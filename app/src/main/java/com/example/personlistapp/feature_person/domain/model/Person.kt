package com.example.personlistapp.feature_person.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    val name: String,
    val age: String,
    val dateOfBirth: String,
    val cpf: String,
    val city: String,
    val photoBytes: ByteArray? = null,
    val enabled: Int? = 1,
    @PrimaryKey val id: Int? = null
)