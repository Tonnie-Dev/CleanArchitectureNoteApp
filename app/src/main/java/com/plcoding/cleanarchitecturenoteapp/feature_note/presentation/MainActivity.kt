package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.NotesScreen
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util.Screens
import com.plcoding.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.EntryPoint

@EntryPoint // to inject viewModel with Dagger Hilt into this activity
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {

                Surface(color = MaterialTheme.colors.background) {


                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screens.NotesScreen.route){

                        composable(Screens.NotesScreen.route){

                            NotesScreen(navController = navController)
                        }

                        composable(route = Screens.AddEditNoteScreen.route +

                        "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(navArgument(name = "noteId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }, navArgument(name = "noteColor"){

                            type = NavType.IntType
                            defaultValue = -1
                        })
                            ){

                            val color = it.arguments?.getInt("noteColor")?: -1

                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }

                    }
                }
                
            }
        }
    }
}
