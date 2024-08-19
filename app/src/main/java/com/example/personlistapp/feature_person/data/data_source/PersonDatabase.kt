package com.example.personlistapp.feature_person.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.personlistapp.feature_person.data.data_source.converters.Converters
import com.example.personlistapp.feature_person.domain.model.Person

@Database(entities = [Person::class], version = 1)
@TypeConverters(Converters::class)
abstract class PersonDatabase: RoomDatabase() {

    abstract val personDao: PersonDao

    companion object {
        const val DATABASE_NAME = "persons_db"
    }
}