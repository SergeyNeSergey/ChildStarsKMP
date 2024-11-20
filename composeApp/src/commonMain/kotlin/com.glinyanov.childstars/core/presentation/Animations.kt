package com.glinyanov.childstars.core.presentation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith

object AppAnimations {
    fun getViewStateChangeAnim(): ContentTransform {
        return fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
    }
}
