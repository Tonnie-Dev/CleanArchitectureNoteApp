package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    //get references for the states from the ViewModel

    val titleState by viewModel.noteTitle
    val contentState by viewModel.noteContent

//Contains basic screen state, e.g. Drawer configuration,sizes of components
    val scaffoldState = rememberScaffoldState()


    val noteBackgroundAnimatable = remember {
        //initial color to be animated
        Animatable(Color(if (noteColor != -1) noteColor else viewModel.noteColor.value))
    }

    //to animate the Animatable Color we need a CoroutineScope
    val scope = rememberCoroutineScope()


    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                }) {

                //content of Fab
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save Note"
                )

            }
        },

        scaffoldState = scaffoldState
    ) {
        
        //Scaffold Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(it)
        ) {

            
            
            //Color Row
            
            Row(modifier = Modifier.fillMaxWidth()) {
                
            }
        }

    }
}