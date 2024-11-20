package com.glinyanov.childstars.core.presentation.design

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.glinyanov.childstars.core.presentation.AppColors
import com.glinyanov.childstars.core.presentation.AppFontFamily

data class AppAlertDialogVo(
    val title: String? = null,
    val message: String? = null,
    val positiveButtonText: String,
    val negativeButtonText: String? = null,
)

@Composable
fun AppAlertDialog(
    modifier: Modifier = Modifier,
    vo: AppAlertDialogVo,
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    onNegativeClick: (() -> Unit)?,
) {

    AlertDialog(
        modifier = modifier,
        title = vo.title?.let {
            {
                Text(
                    text = vo.title,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = AppColors.TextColor,
                    fontFamily = AppFontFamily(),
                )
            }
        },
        text = vo.message?.let {
            {
                Text(
                    text = vo.message,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = AppColors.TextColor,
                    fontFamily = AppFontFamily(),
                )
            }
        },
        onDismissRequest = onDismiss,
        containerColor = AppColors.White,
        confirmButton = {
            AppButton(
                style = AppButtonStyle.Common, text = vo.positiveButtonText, onClick = onPositiveClick
            )
        },
        dismissButton = if (vo.negativeButtonText != null && onNegativeClick != null) {
            {
                AppButton(style = AppButtonStyle.CommonV2,
                    text = vo.negativeButtonText,
                    onClick = onNegativeClick
                )
            }
        } else {
            null
        }
    )
}

@Composable
fun AppAlertDialogPreview() {
    AppAlertDialog(
        vo = AppAlertDialogVo(
            title = "Заголовок",
            message = "Послание народу. Прочти внимательно",
            positiveButtonText = "Сюда жми",
            negativeButtonText = "Сюда не жми",
        ),
        onDismiss = {},
        onPositiveClick = {},
        onNegativeClick = {},
    )
}