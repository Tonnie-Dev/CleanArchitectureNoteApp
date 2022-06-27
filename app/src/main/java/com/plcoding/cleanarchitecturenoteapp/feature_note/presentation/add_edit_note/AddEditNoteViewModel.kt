package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNoteByIdUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCase: NotesUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    //state
    var noteTitle by mutableStateOf(NoteTextFieldState(hint = "Enter title ..."))
        private set

    var noteContent by mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
        private set

    //toArg() converts Jetpack color to int
    var noteColor by mutableStateOf(Note.noteColors.random().toArgb())
        private set


    var eventFlow = MutableSharedFlow<UIEvent>()
        private set

    //cache note id
    private var currentNoteId: Int? = null

    init {

        //get id from nav
        savedStateHandle.get<Int>("noteId")?.let { noteId ->


            //default passed id is -1

            if (noteId != -1) {

                //launch a coroutine

                viewModelScope.launch {

                    // TODO: 23-Nov-21 test let usage instead of also
//usecase's invoke() is a suspend fxn and needs to be launched in coroutine
                    useCase.getNoteByIdUseCase(noteId)?.also{ note ->

                        currentNoteId = note.id
                        noteTitle =
                            noteTitle.copy(text = note.title, isHintVisible = false)

                        noteContent = noteContent.copy(text = note.content, isHintVisible = false)

                        noteColor = note.color

                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {

        when (event) {
//update title
            is AddEditNoteEvent.EnteredTitle -> {

                noteTitle = noteTitle.copy(text = event.value)
            }

            //hide hint if focussed and title is blank
            is AddEditNoteEvent.ChangeTitleFocus -> {


                noteTitle =
                    noteTitle.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                noteTitle.text.isBlank()
                    )
            }

            //update content
            is AddEditNoteEvent.EnteredContent -> {

                noteContent = noteContent.copy(text = event.value)
            }

            //hide hint on the content TextField
            is AddEditNoteEvent.ChangeContentFocus -> {


                noteContent = noteContent.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.text.isBlank()
                )
            }

            //update note's color
            is AddEditNoteEvent.ChangeColor -> {


                noteColor = event.color
            }

            //save note into db
            is AddEditNoteEvent.SaveNote -> {


                //launch coroutine
                viewModelScope.launch {


                    try {

                        useCase.addNoteUseCase(
                            Note(
                                title = noteTitle.text,
                                content = noteContent.text,
                                timeStamp = System.currentTimeMillis(),
                                color = noteColor,
                                id = currentNoteId
                            )
                        )

                        eventFlow.emit((UIEvent.SaveNote))
                    } catch (e: InvalidNoteException) {

                        eventFlow.emit(

                            UIEvent.ShowSnackbar(message = e.message ?: "Couldn't save the note")
                        )

                    }
                }
            }

        }
    }

    sealed class UIEvent {

        //Events for add_edit note screen
        data class ShowSnackbar(val message: String) : UIEvent()
        object SaveNote : UIEvent()
    }

}