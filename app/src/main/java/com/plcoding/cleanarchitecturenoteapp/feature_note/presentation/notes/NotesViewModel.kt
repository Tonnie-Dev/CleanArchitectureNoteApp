package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val notesUseCases: NotesUseCase) : ViewModel() {

    //keep a reference to the last deleted note

    var recentlyDeletedNote: Note? = null

    //state comes down & will be observed by the UI

    var state = mutableStateOf(NotesState())
        private set


    //events from UI
    fun onEvent(event: NotesEvent) {

        //events from the UI - Events go up
        when (event) {


            is NotesEvent.Order -> {}
            is NotesEvent.DeleteNote -> {

                viewModelScope.launch {

                    notesUseCases.deleteNoteUseCase(event.note)

                }

            }
            is NotesEvent.RestoreNote -> {

                state.value
            }
            is NotesEvent.ToggleOrderSelection -> {


                //we take the value of object an copy it giving us
                //the ability to change the object's value

                //state.value = NotesState().copy(isOrderSelectionVisible = !true)

                state.value =
                    NotesState(isOrderSelectionVisible = !state.value.isOrderSelectionVisible)
            }
        }
    }
}