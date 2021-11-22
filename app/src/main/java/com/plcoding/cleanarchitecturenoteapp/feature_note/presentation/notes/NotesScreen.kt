package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

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
import androidx.navigation.NavController

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

        Column(modifier = Modifier

            .fillMaxSize()
            .padding(it)) {


            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Your Notes", style = MaterialTheme.typography.h4)


                   IconButton(onClick = { /*TODO*/ }) {
                   
                                       Icon(imageVector = Icons.Default.Sort,
                                  
                                           contentDescription ="Sort" )
                                   }
            }
            
            
        }
    }

}