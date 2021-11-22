package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation

import androidx.lifecycle.ViewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val useCase:GetNoteByIdUseCase) :
    ViewModel() {
}