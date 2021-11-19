package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import androidx.compose.ui.text.toLowerCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class GetNotesUseCase(private val repo: NoteRepository) {

    //set default order by date - descending

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)):
            Flow<List<Note>> {

        //return the list from the repository
        return repo.getNotes()
        //then map the returned notes/ flow
            .map { notes ->

                //determine order type (Descending or Ascending)
                when(noteOrder.orderType){

                    //ORDER_TYPE ASCENDING
                    is OrderType.Ascending -> {

                        when(noteOrder){
                            is NoteOrder.Title -> notes.sortedBy {
                                note -> note.title.lowercase()
                            }
                            is NoteOrder.Date -> notes.sortedBy {
                                note -> note. timeStamp
                            }

                            is NoteOrder.Color -> notes.sortedBy {
                                note -> note.color
                            }
                        }

                    }

                    //ORDER_TYPE ASCENDING
                    is OrderType.Descending -> {

                        when(noteOrder){

                            is NoteOrder.Title -> notes.sortedByDescending {

                                note -> note.title
                            }
                            is NoteOrder.Date -> notes.sortedByDescending {
                                note -> note.timeStamp
                            }

                            is NoteOrder.Color -> notes.sortedByDescending {
                                note -> note.color
                            }
                        }


                    }


                }



            }
    }
}