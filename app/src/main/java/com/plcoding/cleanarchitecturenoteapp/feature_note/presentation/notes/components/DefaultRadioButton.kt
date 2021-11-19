package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DefaultRadioButton(text:String,
                       selected:Boolean,
                       onSelected:()-> Unit,
                       modifier: Modifier = Modifier
) {

    //for radio button and text - align children vertical_centere

    Row( verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        //1st child - Radio Button
        RadioButton(
            selected = selected,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor =MaterialTheme.colors.onBackground

            )
        )

        //spacer
        Spacer(modifier = Modifier.width(8.dp))

        //2nd child - Text
        Text(text = text, style = MaterialTheme.typography.body1)

    }
}