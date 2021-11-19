package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note


@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDelete: () -> Unit
) {


    Box(modifier = modifier){

Canvas(modifier = Modifier.matchParentSize()){

    val clipPath = Path().apply {

        lineTo(size.width - cutCornerSize.toPx(), 0f)
        lineTo(size.width, cutCornerSize.toPx())
        lineTo(size.width, size.height )
        lineTo(0f, size.height)
        close()
    }


    clipPath(path = clipPath){

        drawRoundRect(
            size = size,
            color = Color(note.color),
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )

        drawRoundRect(
            size = size,
            color = Color(ColorUtils.blendARGB(note.color, 0x000000,0.2f)),
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }

}

    }
}