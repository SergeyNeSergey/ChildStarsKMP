package com.glinyanov.childstars

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.glinyanov.childstars.app.App
import com.glinyanov.childstars.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ChildStars",
        ) {
            App()
        }
    }
}