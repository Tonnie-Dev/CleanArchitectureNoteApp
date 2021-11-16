package com.plcoding.cleanarchitecturenoteapp.di

import android.content.Context
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
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


    //ROOM
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
}

