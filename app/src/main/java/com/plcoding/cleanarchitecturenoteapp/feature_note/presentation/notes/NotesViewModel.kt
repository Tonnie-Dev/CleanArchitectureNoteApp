package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(notesUseCase: GetNotesUseCase): ViewModel() {

    //state comes down & will be observed by the UI

    var state = mutableStateOf(NotesState())
    private set


//events from UI
    fun onEvent(event: NotesEvent){

    //events from the UI - Events go up
        when(event){


            is NotesEvent.Order ->{}
            is NotesEvent.DeleteNote ->{}
            is NotesEvent.RestoreNote ->{}
            is NotesEvent.ToggleOrderSelection ->{}
        }
    }
}