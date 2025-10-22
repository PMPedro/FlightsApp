package com.example.flightsapp.ui.theme

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween

object AppAnimation {
    val Fast = tween<Int>(150, easing = EaseInOut)
    val Normal = tween<Int>(300, easing = EaseInOut)
    val Slow = tween<Int>(500, easing = EaseInOut)
}