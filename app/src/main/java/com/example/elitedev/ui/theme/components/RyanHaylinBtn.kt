package com.example.elitedev.ui.theme.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

@Composable
fun RyanHaylinBtn(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp
        )
    }

}