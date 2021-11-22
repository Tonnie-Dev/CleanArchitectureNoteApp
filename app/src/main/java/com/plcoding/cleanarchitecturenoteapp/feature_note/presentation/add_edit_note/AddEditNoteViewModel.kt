package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.lifecycle.ViewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddEditNoteViewModel(private val useCase: GetNoteByIdUseCase):ViewModel(){
}