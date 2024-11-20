package com.glinyanov.childstars.features.aboutchilds.domain

sealed class AboutChildsErrors : Exception() {
    data object OtpError : AboutChildsErrors()
    data object AboutChildError : AboutChildsErrors()
    data object Forbidden : AboutChildsErrors()
}