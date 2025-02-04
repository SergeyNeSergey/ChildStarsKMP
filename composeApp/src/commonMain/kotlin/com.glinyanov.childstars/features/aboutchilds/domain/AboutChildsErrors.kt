package com.glinyanov.childstars.features.aboutchilds.domain

sealed class AboutChildsErrors(message: String?) : Exception(message) {
    class OtpError(message: String?) : AboutChildsErrors(message)
    class AboutChildError(message: String?) : AboutChildsErrors(message)
    class Forbidden(message: String?) : AboutChildsErrors(message)
}