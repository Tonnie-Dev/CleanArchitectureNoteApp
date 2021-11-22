package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components.OrderSection

@ExperimentalAnimationApi
@Composable
fun NotesScreen(navController: NavController, viewModel: NotesViewModel) {

//variables
    val state by viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    //Scaffold
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            //Content of FAB
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note"
            )
        }
    }) {

        Column(
            modifier = Modifier

                .fillMaxSize()
                .padding(it)
        ) {

//HEADER SECTION
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Your Notes", style = MaterialTheme.typography.h4)


                IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSelection) }) {

                    Icon(
                        imageVector = Icons.Default.Sort,

                        contentDescription = "Sort"
                    )
                }
            }

            //ORDER SECTION
            AnimatedVisibility(
                visible = state.isOrderSelectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {


                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange ={

                        viewModel.onEvent(NotesEvent.Order(it))}
                )
            }
        }
    }

}