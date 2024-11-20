package com.glinyanov.childstars.features.aboutchilds.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.glinyanov.childstars.core.presentation.Primary

internal data class ChildItemVo(
    val name: String,
    val id: String,
)

@Composable
internal fun ChildItem(
    modifier: Modifier = Modifier,
    vo: ChildItemVo,
    onClick: (ChildItemVo) -> Unit,
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(containerColor = Primary),
        onClick = { onClick.invoke(vo) },
    ) {
        Text(text = vo.name, color = Color.White)
    }
}