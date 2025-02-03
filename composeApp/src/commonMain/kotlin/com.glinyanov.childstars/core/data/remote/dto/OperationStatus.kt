package com.glinyanov.childstars.core.data.remote.dto

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline


@Serializable
@JvmInline
value class OperationStatus(val value: String) {

	companion object {
		const val FORBIDDEN = "FORBIDDEN"
		const val SUCCESS = "SUCCESS"
	}
}
