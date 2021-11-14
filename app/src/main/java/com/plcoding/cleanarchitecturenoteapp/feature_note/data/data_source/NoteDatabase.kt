package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase() {

    //Property must be initialized or be abstrac
 abstract val noteDAO:NoteDAO

}