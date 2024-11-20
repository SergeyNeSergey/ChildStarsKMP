package com.glinyanov.childstars

import androidx.compose.ui.window.ComposeUIViewController
import com.glinyanov.childstars.app.App
import com.glinyanov.childstars.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }