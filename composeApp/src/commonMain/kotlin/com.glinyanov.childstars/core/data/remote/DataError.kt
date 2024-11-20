package com.glinyanov.childstars.core.data.remote

import kotlinx.io.IOException

class UnauthorizedDataError: IOException()
class TooManyAttempts: IOException()