package com.glinyanov.childstars.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.open_sans_bold
import childstars.composeapp.generated.resources.open_sans_extra_bold
import childstars.composeapp.generated.resources.open_sans_light
import childstars.composeapp.generated.resources.open_sans_regular
import childstars.composeapp.generated.resources.open_sans_semi_bold
import org.jetbrains.compose.resources.Font

@Composable
fun AppFontFamily(): FontFamily {
    return FontFamily(
        Font(
            resource = Res.font.open_sans_semi_bold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal,
        ),
        Font(
            resource = Res.font.open_sans_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        ),
        Font(
            resource = Res.font.open_sans_extra_bold,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal,
        ),
        Font(
            resource = Res.font.open_sans_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal,
        ),
        Font(
            resource = Res.font.open_sans_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal,
        ),
    )
}