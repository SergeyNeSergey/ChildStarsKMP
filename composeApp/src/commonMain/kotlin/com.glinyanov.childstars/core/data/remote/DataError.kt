package com.glinyanov.childstars.core.data.remote

import kotlinx.io.IOException

class UnauthorizedDataIOException(message: String?): IOException(message)
class TooManyAttemptsIOException(message: String?): IOException(message)