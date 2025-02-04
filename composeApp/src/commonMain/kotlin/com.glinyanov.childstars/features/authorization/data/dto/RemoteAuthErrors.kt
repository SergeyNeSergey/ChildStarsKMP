package com.glinyanov.childstars.features.authorization.data.dto

import kotlinx.io.IOException

internal class BlockedIOException(message: String?) : IOException(message)
internal class IncorrectPasswordIOException(message: String?) : IOException(message)
internal class AlreadyUsedIOException(message: String?) : IOException(message)