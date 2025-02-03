package com.glinyanov.childstars.features.authorization.data.dto

import kotlinx.io.IOException

internal class BlockedIOException : IOException()
internal class IncorrectPasswordIOException : IOException()
internal class AlreadyUsedIOException : IOException()