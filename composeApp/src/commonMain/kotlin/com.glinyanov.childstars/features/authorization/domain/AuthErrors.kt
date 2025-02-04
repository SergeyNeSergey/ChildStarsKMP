package com.glinyanov.childstars.features.authorization.domain

sealed class AuthErrors(message: String?) : Exception(message) {
    class SomethingWrong(message: String?) : AuthErrors(message)
    class TimeOut(message: String?) : AuthErrors(message)
    class IncorrectData(message: String?) : AuthErrors(message)
    class UnknownUser(message: String?) : AuthErrors(message)
    class IncorrectOtp(message: String?) : AuthErrors(message)
    class IncorrectName(message: String?) : AuthErrors(message)
    class ToManyAttempts(message: String?) : AuthErrors(message)
    class IncorrectEmail(message: String?) : AuthErrors(message)
    class PasswordExpired(message: String?) : AuthErrors(message)
    class Forbidden(message: String?) : AuthErrors(message)
}