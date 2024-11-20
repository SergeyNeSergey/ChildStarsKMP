package com.glinyanov.childstars.features.aboutchilds.presentation.items

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glinyanov.childstars.core.presentation.AppAnimations
import com.glinyanov.childstars.core.presentation.AppColors
import com.glinyanov.childstars.core.presentation.AppFontFamily
import com.glinyanov.childstars.core.presentation.design.AppButton
import com.glinyanov.childstars.core.presentation.design.AppButtonStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

data class CodeItemVo(
    val state: State,
    val hintMessage: String = "",
    val checkButtonText: String = "",
    val codeText: String = "",
) {
    enum class State {
        Hidden, Loading, Show
    }
}

@Composable
internal fun CodeItem(
    modifier: Modifier = Modifier,
    vo: CodeItemVo,
    onCheckClick: () -> Unit,
) {
    AnimatedContent(
        targetState = vo.state,
        transitionSpec = { AppAnimations.getViewStateChangeAnim() },
    ) { state ->
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            when (state) {
                CodeItemVo.State.Hidden -> Unit
                CodeItemVo.State.Loading -> CircularProgressIndicator(Modifier.height(50.dp))
                CodeItemVo.State.Show -> {
                    Row {
                        Column {
                            var buttonSize: Int by remember {
                                mutableStateOf(0)
                            }
                            Text(
                                modifier = Modifier
                                    .background(AppColors.White, RoundedCornerShape(10.dp))
                                    .onGloballyPositioned { buttonSize = it.size.width }
                                    .padding(horizontal = 15.dp, vertical = 5.dp),
                                text = vo.codeText,
                                color = AppColors.Purple,
                                fontSize = 32.sp,
                                letterSpacing = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppFontFamily(),
                            )

                            AppButton(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .width(with(LocalDensity.current) { buttonSize.toDp() }),
                                style = AppButtonStyle.Common,
                                text = vo.checkButtonText,
                                onClick = onCheckClick,
                            )
                        }

                        Text(
                            modifier = Modifier.align(Alignment.Top).padding(horizontal = 10.dp),
                            text = vo.hintMessage,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = AppColors.Purple,
                            fontFamily = AppFontFamily(),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CodeItemPreview() {
    CodeItem(
        vo = CodeItemVo(
            state = CodeItemVo.State.Show,
            hintMessage = "Введите этот код при регистрации на устройстве ребенка",
            checkButtonText = "Проверить",
            codeText = "1234"
        )
    ) {}
}