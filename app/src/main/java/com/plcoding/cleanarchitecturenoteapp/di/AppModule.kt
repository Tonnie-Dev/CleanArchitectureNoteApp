package com.plcoding.cleanarchitecturenoteapp.di

import android.content.Context
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

//scope this to the application lifecycle to live throughout the app life
@InstallIn(SingletonComponent::class)

// make the module an object which cannot be instantiated
object AppModule {


    //PROVIDER 1 - ROOM
    @Provides
    @Singleton

    //method returns the abstract data base class

    //the method takes a context to create the RoomDatabase
    fun provideRoomDatabase(@ApplicationContext context: Context): NoteDatabase {

        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        )
            .build()

    }

    //PROVIDER 2 - REPO

    /*Repo needs a DAO
    * Because we haven't provided the DAO here instead
    * we just provided NoteDatabase so we simply pass
    * the database and retrieve the DAO from it.
    * */
    @Provides
    @Singleton
    fun provideNotesRepository(db: NoteDatabase): NoteRepository {


        return NoteRepositoryImpl(db.noteDAO)
    }

    //PROVIDER 3 - USE_CASES - returns the Use Cases Container
    @Provides
    @Singleton
    fun providesUseCasesContainer(
       repository: NoteRepository
    ): NotesUseCase {

        return NotesUseCase(
            deleteNoteUseCase =DeleteNoteUseCase(repo = repository),
           getNotesUseCase = GetNotesUseCase(repo = repository),
            addNoteUseCase = AddNoteUseCase(repo = repository),
            getNoteByIdUseCase = GetNoteByIdUseCase(repo = repository)
            )

    }


}

