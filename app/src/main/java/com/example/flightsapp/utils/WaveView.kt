package com.example.flightsapp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times


@Composable
fun WaveCanvas (wave: Animatable<Float, AnimationVector1D> , screenHeight : Dp, color : Color){
    Canvas(
        modifier = Modifier.fillMaxWidth().height(wave.value * screenHeight)
    ){

        val width = size.width
        val height = size.height
        val path = Path().apply {
            moveTo(0f, height * 2f)
            quadraticTo(width * 0f, height * 1f, width * 1, height * 0.5f)
            quadraticTo(width * 0.0f, height * 0.5f, width , height * 0.50f)
            lineTo(width * 1f, 0f)
            lineTo(width * 0f, 0f)
            close()
        }
        drawPath(path, color)
    }
}
