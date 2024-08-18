package com.example.personlistapp.feature_person.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.personlistapp.feature_person.domain.model.Person

@Database(
    entities = [Person::class],
    version = 1
)
abstract class PersonDatabase: RoomDatabase() {

    abstract val personDao: PersonDao
}