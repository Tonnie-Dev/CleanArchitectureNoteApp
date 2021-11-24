package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    //show once
    LaunchedEffect(key1 = true ){

        viewModel.eventFlow.collectLatest {

            event ->

            when(event){


                is AddEditNoteViewModel.UIEvent.ShowSnackbar -> {


                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                is AddEditNoteViewModel.UIEvent.SaveNote -> {

                    navController.navigateUp()
                }
            }
        }

    }


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
    ) { paddingValues ->

        //Scaffold Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(paddingValues)
        ) {


            //Color Row

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach {

                        color ->

                    //convert to compose color
                    val colorInt = color.toArgb()

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(elevation = 15.dp, shape = CircleShape)
                            .clip(shape = CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                shape = CircleShape,
                                color = if (viewModel.noteColor.value == colorInt)
                                    Color.Black else Color.Transparent
                            )
                            .clickable {


                                //we want to animate our color to a new color

                                //animateTo() is a suspend fxn
                                scope.launch {

                                    noteBackgroundAnimatable.animateTo(
                                        //target value will be our new color
                                        targetValue = Color(color = colorInt),
                                        animationSpec = tween(durationMillis = 500)
                                    )

                                }
                                viewModel.onEvent(event = AddEditNoteEvent.ChangeColor(color = colorInt))
                            }
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                                viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))

                },
                onFocusChange = {


                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                }, isHintVisible = titleState.isHintVisible
            , singleLine = true, textStyle = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {

                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }

    }
}