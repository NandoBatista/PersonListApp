package com.example.personlistapp.di

import android.app.Application
import androidx.room.Room
import com.example.personlistapp.feature_person.data.data_source.PersonDatabase
import com.example.personlistapp.feature_person.data.repository.PersonRepositoryImpl
import com.example.personlistapp.feature_person.domain.repository.PersonRepository
import com.example.personlistapp.feature_person.domain.use_case.AddPersonUseCase
import com.example.personlistapp.feature_person.domain.use_case.DeletePersonUseCase
import com.example.personlistapp.feature_person.domain.use_case.GetPersonUseCase
import com.example.personlistapp.feature_person.domain.use_case.GetPersonsUseCase
import com.example.personlistapp.feature_person.domain.use_case.PersonUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePersonDatabase(app: Application): PersonDatabase {
        return Room.databaseBuilder(
            app,
            PersonDatabase::class.java,
            PersonDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonRepository(db: PersonDatabase): PersonRepository {
        return PersonRepositoryImpl(db.personDao)
    }

    @Provides
    @Singleton
    fun providePersonUseCases(repository: PersonRepository): PersonUseCases {
        return PersonUseCases(
            getPersons = GetPersonsUseCase(repository),
            deletePerson = DeletePersonUseCase(repository),
            addPerson = AddPersonUseCase(repository),
            getPerson = GetPersonUseCase(repository)
        )
    }

}