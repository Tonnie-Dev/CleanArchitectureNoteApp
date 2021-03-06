package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase (private val repo: NoteRepository){

//make invoke fxn throw an exemption

    @Throws (InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
// we only want to add the note once we have validate the input
        //logic for note validation
        if (note.title.isBlank()){

            throw InvalidNoteException(message = "The title of the note cannot be empty")
        }
        if (note.content.isBlank()){


            throw InvalidNoteException(message = "The content of the note cannot be empty")
        }

//if the above 2 ifs are false we just insert the note
        repo.insertNote(note)
    }

}