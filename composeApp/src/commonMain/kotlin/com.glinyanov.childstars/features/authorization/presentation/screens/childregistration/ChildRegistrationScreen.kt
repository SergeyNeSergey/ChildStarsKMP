package com.glinyanov.childstars.features.authorization.presentation.screens.childregistration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.glinyanov.childstars.core.presentation.design.AppAlertDialog
import com.glinyanov.childstars.core.presentation.design.AppAlertDialogVo
import com.glinyanov.childstars.core.presentation.design.AppButton
import com.glinyanov.childstars.core.presentation.design.AppNavbar
import com.glinyanov.childstars.core.presentation.design.AppNavbarItemVo
import com.glinyanov.childstars.core.presentation.design.AppTextField

internal data class ChildRegistrationScreenVo(
    val navBarItems: List<AppNavbarItemVo>,
    val childNameHint: String,
    val childNameMessage: String,
    val codeHint:String,
    val codeMessage: String,
    val loginButtonText: String,
    val isFieldsNotEmpty: Boolean,
    val alertDialogVo: AppAlertDialogVo?,
)

@Composable
internal fun ChildRegistrationScreen(viewModel: ChildRegistrationViewModel) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    if (screenState is ChildRegistrationViewModel.State.Content) {
        val state = screenState as ChildRegistrationViewModel.State.Content
        Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState(), enabled = true)
            ) {
                val childNameFocusRequester = remember { FocusRequester() }
                val passwordFocusRequester = remember { FocusRequester() }
                val keyboardController = LocalSoftwareKeyboardController.current

                AppNavbar(state.data.navBarItems)
                AppTextField(
                    modifier = Modifier
                        .focusRequester(childNameFocusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    value = state.data.childNameMessage,
                    onValueChange = viewModel::onChildNameFieldTextChanged,
                    label = state.data.childNameHint,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when {
                                state.data.isFieldsNotEmpty -> {
                                    viewModel.onCheckEnteredFieldsClick()
                                    keyboardController?.hide()
                                }

                                state.data.codeMessage.isBlank() -> {
                                    passwordFocusRequester.requestFocus()
                                }
                            }
                        }
                    )
                )
                AppTextField(
                    modifier = Modifier
                        .focusRequester(passwordFocusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = state.data.codeMessage,
                    onValueChange = viewModel::onChildCodeFieldTextChanged,
                    label = state.data.codeHint,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when {
                                state.data.isFieldsNotEmpty -> {
                                    viewModel.onCheckEnteredFieldsClick()
                                    keyboardController?.hide()
                                }

                                state.data.childNameMessage.isBlank() -> {
                                    childNameFocusRequester.requestFocus()
                                }
                            }
                        }
                    )
                )

                AppButton(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 25.dp),
                    text = state.data.loginButtonText,
                    onClick = viewModel::onCheckEnteredFieldsClick
                )
            }
            state.data.alertDialogVo?.let {
                AppAlertDialog(
                    vo = it,
                    onDismiss = viewModel::onAlertDialogDismiss,
                    onNegativeClick = viewModel::onAlertDialogNegativeButtonClick,
                    onPositiveClick = viewModel::onAlertDialogPositiveButtonClick,
                )
            }
        }
    }
}
