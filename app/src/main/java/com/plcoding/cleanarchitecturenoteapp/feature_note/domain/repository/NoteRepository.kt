package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


/*This is declared as an interface to allow testing and creating
fake version of this repository.

This test version should be quick and simulate the real repo
 */
interface NoteRepository {
    //not a suspend fxn as it returns a flow
    fun getNotes(): Flow<List<Note>>

    //return nullable if not doesn't exist
    suspend fun getNoteById(id: Int): Note?


    //pass note to insert into database
    suspend fun insertNote(note: Note)

    //pass note to delete from database
    suspend fun deleteNote(note: Note)
}



/*
            val currencyList: List<String> = when (countryJsonObj.has("currencies")) {

                true -> {

                    val list = mutableListOf<String>()
                    list.clear()
                    val currencyData = countryJsonObj.getJSONObject("currencies")

                    currencyData.keys()
                            .forEach { curr ->

                                val currencyNameObj = currencyData.getJSONObject(curr)

                                val currency = currencyNameObj.getString("name")
                                list.add(currency)


                            }
                    list
                }
                false -> {
                    listOf("Not found")
                }

            }*/