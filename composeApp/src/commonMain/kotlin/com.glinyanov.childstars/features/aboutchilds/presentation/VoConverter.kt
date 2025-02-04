package com.glinyanov.childstars.features.aboutchilds.presentation

import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.alert_btn_positive_text
import childstars.composeapp.generated.resources.alert_error_forbidden_message
import childstars.composeapp.generated.resources.alert_error_network
import childstars.composeapp.generated.resources.alert_error_otp
import childstars.composeapp.generated.resources.main_logo
import childstars.composeapp.generated.resources.registration_add_child
import childstars.composeapp.generated.resources.registration_check_child_code
import childstars.composeapp.generated.resources.registration_child_code_description
import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.core.presentation.design.AppAlertDialogVo
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsErrors
import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildListItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.CodeItemVo
import org.jetbrains.compose.resources.getString

internal class VoConverter {

    suspend fun toVo(
        otp: OtpDo?,
        isOtpLoading: Boolean,
        childs: List<ChildDo>,
        isChildsLoading: Boolean,
        error: AboutChildsErrors?,
    ): ChildListScreenVo {
        return ChildListScreenVo(
            drawableRes = Res.drawable.main_logo,
            addChildText = getString(Res.string.registration_add_child),
            codeItemVo = toVo(otp, isOtpLoading),
            childListVo = toVo(childs, isChildsLoading),
            alertDialogVo = error?.let { toVo(it) }
        )
    }

    private fun toVo(domainObject: List<ChildDo>, isLoading: Boolean): ChildListItemVo {
        return ChildListItemVo(
            isChildListLoading = isLoading,
            domainObject.map(::toVo)
        )
    }

    private fun toVo(domainObject: ChildDo): ChildItemVo {
        return ChildItemVo(
            name = domainObject.name,
            id = domainObject.id,
        )
    }

    private suspend fun toVo(error: AboutChildsErrors): AppAlertDialogVo {
        return AppAlertDialogVo(
            title = error.message ?: when (error) {
                is AboutChildsErrors.OtpError -> getString(Res.string.alert_error_otp)
                is AboutChildsErrors.AboutChildError -> getString(Res.string.alert_error_network)
                is AboutChildsErrors.Forbidden -> getString(Res.string.alert_error_forbidden_message)
            },
            positiveButtonText = getString(Res.string.alert_btn_positive_text)
        )
    }

    private suspend fun toVo(domainObject: OtpDo?, isLoading: Boolean): CodeItemVo {
        return when {
            isLoading -> CodeItemVo(state = CodeItemVo.State.Loading)
            domainObject != null -> CodeItemVo(
                state = CodeItemVo.State.Show,
                hintMessage = getString(Res.string.registration_child_code_description),
                checkButtonText = getString(Res.string.registration_check_child_code),
                codeText = domainObject.otp
            )

            else -> CodeItemVo(state = CodeItemVo.State.Hidden)
        }
    }
}