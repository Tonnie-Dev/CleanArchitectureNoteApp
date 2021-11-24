package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util


//contains screens and their routes
sealed class Screens(val route:String){

    object NotesScreen:Screens("notes_screen")
    object AddEditNoteScreen:Screens("add_edit_note_screen")
}
