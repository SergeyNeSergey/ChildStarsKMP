package com.glinyanov.childstars.api

import com.google.gson.annotations.SerializedName

class Stock(@SerializedName("id") val id: String,
            @SerializedName("name") val name: String,
            @SerializedName("slug") val slug: String,
            @SerializedName("address") val address: String,
            @SerializedName("country") val country: String,
            @SerializedName("code") val code: String,
            @SerializedName("roles") val roles: List<String>)

//<string name="webview_start_url" translatable="false">https://%s.zappstore.pro/pda/</string>
//<string name="webview_check_url" translatable="false">https://%s.zappstore.pro/</string>
//private val startUrl: String? get() = slug?.let { getString(R.string.webview_start_url, slug) }
//val checkUrl: String? get() = slug?.let { getString(R.string.webview_check_url, slug) }