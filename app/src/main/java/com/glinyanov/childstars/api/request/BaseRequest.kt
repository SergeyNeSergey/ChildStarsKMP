package com.glinyanov.childstars.api.request

import com.google.gson.annotations.SerializedName

open class BaseRequest<T>(@SerializedName("id") val id: String = "JsonRpcClient.js",
                          @SerializedName("jsonrpc") val json: String = "2.0",
                          @SerializedName("method") val method: String,
                          @SerializedName("params") val params: T)