package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {

// TODO: 14-Nov-21 Confirm table Name

    /*This is not a suspend fxn since it is wrapped with Flow from Coroutines*/
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id =:id")
    suspend fun getNoteById(id:Int):Note?
}