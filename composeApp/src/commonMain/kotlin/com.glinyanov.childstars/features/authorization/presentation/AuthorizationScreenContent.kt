package com.glinyanov.childstars.features.authorization.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glinyanov.childstars.core.presentation.AppColors
import com.glinyanov.childstars.core.presentation.AppFontFamily
import com.glinyanov.childstars.core.presentation.design.AppAlertDialog
import com.glinyanov.childstars.core.presentation.design.AppButton
import com.glinyanov.childstars.core.presentation.design.AppTextField
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AuthorizationScreenContent(
    state: AuthorizationViewModel.State.Content,
    onCheckEnteredFieldsClick: () -> Unit,
    onParentRegistrationClick: () -> Unit,
    onChildRegistrationClick: () -> Unit,
    onPasswordFieldTextChanged: (String) -> Unit,
    onLoginFieldTextChanged: (String) -> Unit,
    onAlertDismiss: () -> Unit,
    onAlertPositiveClick: () -> Unit,
    onAlertNegativeClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            val loginFocusRequester = remember { FocusRequester() }
            val passwordFocusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current
            Image(
                painter = painterResource(state.data.drawableRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .padding(30.dp)
            )
            AppTextField(
                modifier = Modifier
                    .focusRequester(loginFocusRequester)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                value = state.data.loginMessage,
                onValueChange = onLoginFieldTextChanged,
                label = state.data.loginHintMessage,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        when {
                            state.data.isFieldsNotEmpty -> {
                                onCheckEnteredFieldsClick.invoke()
                                keyboardController?.hide()
                            }

                            state.data.passwordMessage.isBlank() -> passwordFocusRequester.requestFocus()
                        }
                    }
                )
            )
            AppTextField(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = state.data.passwordMessage,
                onValueChange = onPasswordFieldTextChanged,
                label = state.data.passwordHintMessage,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        when {
                            state.data.isFieldsNotEmpty -> {
                                onCheckEnteredFieldsClick.invoke()
                                keyboardController?.hide()
                            }

                            state.data.loginMessage.isBlank() -> loginFocusRequester.requestFocus()
                        }
                    }
                )
            )

            AppButton(
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 25.dp),
                text = state.data.loginButtonText,
                onClick = onCheckEnteredFieldsClick
            )

            Text(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp)
                    .clickable(onClick = onParentRegistrationClick),
                text = state.data.parentRegistrationButtonText,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = AppColors.Purple,
                fontFamily = AppFontFamily(),
            )

            Text(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                    .clickable(onClick = onChildRegistrationClick),
                text = state.data.childRegistrationButtonText,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = AppColors.Purple,
                fontFamily = AppFontFamily(),
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