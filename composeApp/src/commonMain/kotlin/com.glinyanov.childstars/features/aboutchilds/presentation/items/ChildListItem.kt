package com.glinyanov.childstars.features.aboutchilds.presentation.items

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.glinyanov.childstars.core.presentation.AppAnimations
import com.glinyanov.childstars.core.presentation.ShimmerColorShades
import com.glinyanov.childstars.core.presentation.design.AppButton
import com.glinyanov.childstars.core.presentation.design.AppButtonStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

data class ChildListItemVo(
    val isChildListLoading: Boolean,
    val childList: List<ChildItemVo>,
)

data class ChildItemVo(
    val name: String,
    val id: String,
)

@Composable
internal fun ChildItemsList(
    vo: ChildListItemVo,
    onChildClick: (ChildItemVo) -> Unit,
) {
    AnimatedContent(
        targetState = vo.isChildListLoading,
        transitionSpec = { AppAnimations.getViewStateChangeAnim() },
    ) { state ->
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            if (state) {
                items(5) { ShimmerItem() }
            } else {
                items(vo.childList) { childItemVo ->
                    ChildItem(vo = childItemVo, onClick = onChildClick)
                }
            }
        }

    }

}

@Composable
private fun ShimmerItem() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(bottom = 5.dp)
            .background(brush = brush)
    )
}

@Composable
private fun ChildItem(
    modifier: Modifier = Modifier,
    vo: ChildItemVo,
    onClick: (ChildItemVo) -> Unit,
) {
    AppButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        style = AppButtonStyle.Secondary,
        text = vo.name,
    ) { onClick.invoke(vo) }
}

@Preview
@Composable
private fun ChildItemListPreview() {
    ChildItemsList(
        vo = ChildListItemVo(
            isChildListLoading = false,
            childList = listOf(
                ChildItemVo("Вася Пуковкин", "SomeId"),
                ChildItemVo("Пися Камушкин", "SomeId"),
            ),
        )
    ) {}
}