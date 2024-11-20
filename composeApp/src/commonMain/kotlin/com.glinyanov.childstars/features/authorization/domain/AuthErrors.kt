package com.glinyanov.childstars.features.authorization.domain

sealed class AuthErrors : Exception() {
    data object IncorrectDataError : AuthErrors()
    data object IncorrectOtp : AuthErrors()
    data object IncorrectName : AuthErrors()
    data object ToManyAttempts : AuthErrors()
    data object IncorrectEmail : AuthErrors()
    data object PasswordExpired : AuthErrors()
    data object Forbidden : AuthErrors()
}