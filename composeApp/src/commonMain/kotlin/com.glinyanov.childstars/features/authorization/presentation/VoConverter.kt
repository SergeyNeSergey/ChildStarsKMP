package com.glinyanov.childstars.features.authorization.presentation

import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.alert_btn_positive_text
import childstars.composeapp.generated.resources.alert_confirm_registration_incorrect
import childstars.composeapp.generated.resources.alert_confirm_registration_login_attempts_limit_exceeded
import childstars.composeapp.generated.resources.alert_confirm_registration_time_out
import childstars.composeapp.generated.resources.alert_error_forbidden_message
import childstars.composeapp.generated.resources.alert_error_invalid_message
import childstars.composeapp.generated.resources.alert_letter_language
import childstars.composeapp.generated.resources.alert_registration_email
import childstars.composeapp.generated.resources.main_logo
import childstars.composeapp.generated.resources.registration_child_code_hint
import childstars.composeapp.generated.resources.registration_child_init
import childstars.composeapp.generated.resources.registration_child_name_hint
import childstars.composeapp.generated.resources.registration_email_hint
import childstars.composeapp.generated.resources.registration_name_hint
import childstars.composeapp.generated.resources.registration_parent_init
import childstars.composeapp.generated.resources.registration_password_hint
import childstars.composeapp.generated.resources.registration_title2
import com.glinyanov.childstars.core.presentation.design.AppAlertDialogVo
import com.glinyanov.childstars.core.presentation.design.AppNavbarItemVo
import com.glinyanov.childstars.features.authorization.domain.AuthErrors
import com.glinyanov.childstars.features.authorization.presentation.screens.childregistration.ChildRegistrationScreenVo
import com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration.ParentRegistrationScreenVo
import org.jetbrains.compose.resources.getString

internal class VoConverter {

    suspend fun toVo(
        parentName: String,
        email: String,
        password: String,
        error: AuthErrors?,
        onBackPressed: () -> Unit
    ): ParentRegistrationScreenVo {
        return ParentRegistrationScreenVo(
            navBarItems = listOf(AppNavbarItemVo.BackPressItem(onBackPressed)),
            parentNameHint = getString(Res.string.registration_name_hint),
            parentNameMessage = parentName,
            emailHint = getString(Res.string.registration_email_hint),
            emailMessage = email,
            passwordHint =  getString(Res.string.registration_password_hint),
            passwordMessage = password,
            isFieldsNotEmpty = parentName.isNotBlank() && email.isNotBlank() && password.isNotBlank(),
            loginButtonText = getString(Res.string.registration_title2),
            alertDialogVo = error?.let { toVo(it) },
        )
    }

    suspend fun toVo(
        childName: String,
        otpCode: String,
        error: AuthErrors?,
        onBackPressed: () -> Unit
    ): ChildRegistrationScreenVo {
        return ChildRegistrationScreenVo(
            navBarItems = listOf(AppNavbarItemVo.BackPressItem(onBackPressed)),
            childNameHint = getString(Res.string.registration_child_name_hint),
            childNameMessage = childName,
            codeHint = getString(Res.string.registration_child_code_hint),
            codeMessage = otpCode,
            isFieldsNotEmpty = childName.isNotBlank() && otpCode.isNotBlank(),
            loginButtonText = getString(Res.string.registration_title2),
            alertDialogVo = error?.let { toVo(it) },
        )
    }

    suspend fun toVo(email: String, password: String, error: AuthErrors?): AuthorizationScreenVo {
        return AuthorizationScreenVo(
            drawableRes = Res.drawable.main_logo,
            loginHintMessage = getString(Res.string.registration_email_hint),
            loginMessage = email,
            passwordHintMessage = getString(Res.string.registration_password_hint),
            passwordMessage = password,
            isFieldsNotEmpty = email.isNotBlank() && password.isNotBlank(),
            loginButtonText = getString(Res.string.registration_title2),
            parentRegistrationButtonText = getString(Res.string.registration_parent_init),
            childRegistrationButtonText = getString(Res.string.registration_child_init),
            alertDialogVo = error?.let { toVo(it) }
        )
    }

    private suspend fun toVo(error: AuthErrors): AppAlertDialogVo {
        return AppAlertDialogVo(
            title = when (error) {
                is AuthErrors.IncorrectOtp -> getString(Res.string.alert_confirm_registration_incorrect)
                is AuthErrors.IncorrectDataError -> getString(Res.string.alert_error_invalid_message)
                is AuthErrors.IncorrectName -> getString(Res.string.alert_letter_language)
                is AuthErrors.ToManyAttempts -> {
                    getString(Res.string.alert_confirm_registration_login_attempts_limit_exceeded)
                }

                is AuthErrors.PasswordExpired -> getString(Res.string.alert_confirm_registration_time_out)
                is AuthErrors.IncorrectEmail -> getString(Res.string.alert_registration_email)
                is AuthErrors.Forbidden -> getString(Res.string.alert_error_forbidden_message)
            },
            positiveButtonText = getString(Res.string.alert_btn_positive_text)
        )
    }
}