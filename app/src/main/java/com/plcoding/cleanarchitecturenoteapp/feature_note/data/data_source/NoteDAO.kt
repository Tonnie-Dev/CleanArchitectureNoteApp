package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {


    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>
}