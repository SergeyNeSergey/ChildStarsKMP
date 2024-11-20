package com.glinyanov.childstars.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse<out T>(@SerializedName("result") val result: T?,
                          @SerializedName("error") val error: ApiError?)