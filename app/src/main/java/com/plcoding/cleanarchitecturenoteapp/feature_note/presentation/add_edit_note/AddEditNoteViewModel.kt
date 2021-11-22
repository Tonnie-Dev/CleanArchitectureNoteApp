package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNoteByIdUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val useCase: NotesUseCase) :
    ViewModel() {

    //state
    var noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter title ..."))
        private set

    var noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
    private set

    //toArg() converts Jetpack color to int
    var noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    private set



    sealed class UIEvent{

        //Events for add_edit note screen
        data class ShowSnackbar(val message:String):UIEvent()
        object SaveNote:UIEvent()
    }

}