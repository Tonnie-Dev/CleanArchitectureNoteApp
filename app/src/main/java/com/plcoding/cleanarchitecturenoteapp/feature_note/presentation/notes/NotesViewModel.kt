package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
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


            is NotesEvent.Order -> {

                //check if the radio button option is the same

                if (state.value.noteOrder::class == event.noteOrder::class
                    && state.value.noteOrder.orderType == event.noteOrder.orderType){

                        //do nothing
                    return
                }

            }
            is NotesEvent.DeleteNote -> {

                viewModelScope.launch {

                    notesUseCases.deleteNoteUseCase(event.note)

                    //set last deleted note on deletion event
                    recentlyDeletedNote = event.note
                }

            }
            is NotesEvent.RestoreNote -> {

                viewModelScope.launch {

                    //insert note if not null else return to the call
                    notesUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)

                    //invalidate the note so that it is only inserted once and subsequent
                    //trials will not be inserted

                    recentlyDeletedNote = null
                }
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

    private fun getNotes(noteOrder: NoteOrder){

        notesUseCases.getNotesUseCase(noteOrder = noteOrder)
            .onEach {
                notes ->

                state.value = state.value.copy(notes = notes,
                noteOrder = noteOrder)

            }
    }
}