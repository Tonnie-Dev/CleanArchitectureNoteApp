package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

data class NotesUseCase(
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNotesUseCase: GetNotesUseCase,
    val addNoteUseCase:AddNoteUseCase
)
