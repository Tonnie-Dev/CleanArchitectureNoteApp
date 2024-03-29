package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components.NoteItem
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components.OrderSection
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util.Screens
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalAnimationApi
@Composable
fun NotesScreen(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {

//variables
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    Timber.i("At the Onset length is ${state.notes.size}")

    /*Return a CoroutineScope bound to this point in the composition using
     the optional CoroutineContext provided by getContext. getContext will
     only be called once and the same CoroutineScope instance will be
     returned across recompositions.*/

    val scope = rememberCoroutineScope()
   // Timber.i("Overview Item 1 state: ${state.notes[0].title}")

    //Scaffold
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
//we don't pass any parameters when creating a new note
                navController.navigate(route = Screens.AddEditNoteScreen.route)
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            //Content of FAB
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note"
            )
        }

    },
        //attaching scaffoldState to the Scaffold

        scaffoldState = scaffoldState) {

        Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
        ) {

//HEADER SECTION
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                            .padding(vertical = 8.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {

                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            if (state.notes.isEmpty()){

                Column(Modifier.fillMaxSize()) {

                    Text(text = "Empty")
                }
            }else {

                //NOTES_LIST_SECTION
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(state.notes) { note ->
                        NoteItem(note = note,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {

                                            navController.navigate(
                                                    Screens.AddEditNoteScreen.route +

                                                            "?noteId=${note.id}&noteColor=${note.color}"
                                            )
                                        },

                                onDelete = {



                                    /* showing a snackbar needs a coroutine as it takes
                                      time to show*/

                                    //'showSnackbar' should be called only from a coroutine or another suspend function
                                    scope.launch {
                                        viewModel.onEvent(NotesEvent.DeleteNote(note = note))
                                        //we need to put snackbar as a val to check the action
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                                message = "Note Deleted",
                                                actionLabel = "Undo",

                                                )
                                        Timber.i("At B4R length is ${state.notes.size}")
                                        //check result for action performed
                                        if (result==SnackbarResult.ActionPerformed) {

                                            viewModel.onEvent(NotesEvent.RestoreNote)
                                        }
                                    }

                                    Timber.i("At After Launch length is ${state.notes.size}")


                                }


                        )

                        //put spaces between note items

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }


        }
    }

}