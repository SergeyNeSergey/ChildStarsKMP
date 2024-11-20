package com.glinyanov.childstars.features.aboutchilds.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.glinyanov.childstars.core.presentation.design.AppAlertDialog
import com.glinyanov.childstars.core.presentation.design.AppAlertDialogVo
import com.glinyanov.childstars.core.presentation.design.AppButton
import com.glinyanov.childstars.core.presentation.design.AppButtonStyle
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildItemsList
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildListItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.CodeItem
import com.glinyanov.childstars.features.aboutchilds.presentation.items.CodeItemVo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class ChildListScreenVo(
    val drawableRes: DrawableResource,
    val addChildText: String,
    val codeItemVo: CodeItemVo,
    val childListVo: ChildListItemVo,
    val alertDialogVo: AppAlertDialogVo?,
)

@Composable
fun ChildListScreen(viewModel: ChildListViewModel) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    if (screenState is ChildListViewModel.State.Content) {
        ChildListContentScreen(
            state = screenState as ChildListViewModel.State.Content,
            onChildClick = viewModel::onChildItemClick,
            onAddChildClicked = viewModel::onAddChildClick,
            onCheckCodeClicked = viewModel::onCheckCodeClick,
            onAlertDismiss = viewModel::onAlertDialogDismiss,
            onAlertNegativeClick = viewModel::onAlertDialogNegativeButtonClick,
            onAlertPositiveClick = viewModel::onAlertDialogPositiveButtonClick,
        )
    }
}

@Composable
private fun ChildListContentScreen(
    state: ChildListViewModel.State.Content,
    onAddChildClicked: () -> Unit,
    onCheckCodeClicked: () -> Unit,
    onChildClick: (ChildItemVo) -> Unit,
    onAlertDismiss: () -> Unit,
    onAlertPositiveClick: () -> Unit,
    onAlertNegativeClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
        Column {
            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState(), enabled = true)
            ) {
                Image(
                    painter = painterResource(state.data.drawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                        .padding(vertical = 20.dp, horizontal = 30.dp)
                )
                AppButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = AppButtonStyle.Common,
                    text = state.data.addChildText,
                    onClick = onAddChildClicked,
                )

                CodeItem(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    vo = state.data.codeItemVo,
                    onCheckClick = onCheckCodeClicked,
                )
            }

            ChildItemsList(
                vo = state.data.childListVo,
                onChildClick = onChildClick
            )
        }
        state.data.alertDialogVo?.let {
            AppAlertDialog(
                vo = it,
                onDismiss = onAlertDismiss,
                onNegativeClick = onAlertNegativeClick,
                onPositiveClick = onAlertPositiveClick,
            )
        }
    }
}

@Composable
fun ChildListScreenPreview() {
    ChildListScreen(ChildListViewModelStub)
}