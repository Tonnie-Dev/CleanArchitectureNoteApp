package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController

@Composable
fun NotesScreen(navController: NavController, viewModel: NotesViewModel) {

//variables
    val state by viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


}