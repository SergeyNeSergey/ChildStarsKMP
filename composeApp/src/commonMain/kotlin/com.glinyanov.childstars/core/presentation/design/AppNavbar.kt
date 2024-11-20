package com.glinyanov.childstars.core.presentation.design

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.glinyanov.childstars.core.presentation.AppColors

const val APP_NAVBAR_HEIGHT_DP = 54

@Composable
fun AppNavbar(items: List<AppNavbarItemVo>) {
    Row(
        modifier = Modifier.statusBarsPadding().fillMaxWidth().height(APP_NAVBAR_HEIGHT_DP.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.onEach {
            when(it) {
                is AppNavbarItemVo.BackPressItem -> AppBackPressButton(it)
            }
        }
    }
}

@Composable
private fun RowScope.AppBackPressButton(vo: AppNavbarItemVo.BackPressItem) {
    IconButton(
        onClick = vo.onBackClick,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = AppColors.Purple
        )
    }
}

sealed interface AppNavbarItemVo {
    data class BackPressItem(val onBackClick: () -> Unit): AppNavbarItemVo
}