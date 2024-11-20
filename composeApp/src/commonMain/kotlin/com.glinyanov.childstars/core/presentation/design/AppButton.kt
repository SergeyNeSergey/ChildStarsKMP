package com.glinyanov.childstars.core.presentation.design

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.open_sans_regular
import com.glinyanov.childstars.core.presentation.AppColors
import org.jetbrains.compose.resources.Font
enum class AppButtonStyle {
    Common, CommonV2, Secondary,
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    style: AppButtonStyle = AppButtonStyle.Common,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = when (style) {
                AppButtonStyle.Common -> AppColors.Purple
                AppButtonStyle.Secondary, AppButtonStyle.CommonV2 -> AppColors.Primary
            }
        ),
        onClick = onClick::invoke,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            text = text,
            textAlign = when (style) {
                AppButtonStyle.Common, AppButtonStyle.CommonV2 -> TextAlign.Center
                AppButtonStyle.Secondary -> TextAlign.Left
            },
            color = Color.White,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = FontFamily(
                Font(
                resource = Res.font.open_sans_regular,
                weight = FontWeight.Normal,
                style = FontStyle.Normal,
            ))
        )
    }
}

@Composable
fun AppButtonPreview() {
    AppButton(text = "Ёкарный бабай, жми!") {}
}