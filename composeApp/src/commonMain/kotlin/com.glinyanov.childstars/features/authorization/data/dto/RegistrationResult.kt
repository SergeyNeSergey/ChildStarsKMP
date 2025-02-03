package com.glinyanov.childstars.features.authorization.data.dto

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline


@Serializable
@JvmInline
value class RegistrationResult(val value: String) {

    companion object {
        const val BLOCKED = "BLOCKED"
        const val INCORRECT_PASSWORD = "INCORRECT_PASSWORD"
        const val ATTEMPTS_LIMIT_EXCEEDED = "Registration_ATTEMPTS_LIMIT_EXCEEDED"
        const val TIMEOUT = "TIMEOUT"
        const val ALREADY_USED = "ALREADY_USED"
        const val DONE = "DONE"
    }
}
