package com.glinyanov.childstars.app

import androidx.compose.ui.window.ComposeUIViewController
import com.glinyanov.childstars.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }