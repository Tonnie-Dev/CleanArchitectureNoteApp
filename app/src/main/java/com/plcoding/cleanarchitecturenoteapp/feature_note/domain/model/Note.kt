package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.ui.theme.*

//Mark with @Entity to represent ROOM table
@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,

    @PrimaryKey
    val id: Int? = null
) {


    //Companion Object to hold a list of preset colors

    companion object {

        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }


}


class InvalidNoteException(message:String):Exception(message)