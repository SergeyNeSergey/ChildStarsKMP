package com.glinyanov.childstars.api.enums

import java.io.*
import com.google.gson.annotations.*

enum class OperationStatus(val primitiveValue: Int) : Serializable {
	@SerializedName("Success")
	SUCCESS(0) {
		override val displayNameResourceId = "Success"
	},

	@SerializedName("Forbidden")
	FORBIDDEN(1) {
		override val displayNameResourceId = "Forbidden"
	},

	@SerializedName("InvalidRequest")
	INVALID_REQUEST(2) {
		override val displayNameResourceId = "InvalidRequest"
	},

	@SerializedName("UnsupportedApiVersion")
	UNSUPPORTED_API_VERSION(3) {
		override val displayNameResourceId = "UnsupportedApiVersion"
	},

	@SerializedName("Maintenance")
	MAINTENANCE(4) {
		override val displayNameResourceId = "Maintenance"
	},

	@SerializedName("Failed")
	FAILED(5) {
		override val displayNameResourceId = "Failed"
	};

	abstract val displayNameResourceId: String

	companion object {
		fun fromPrimitiveValue(primitiveValue: Int): OperationStatus {
			return enumValues<OperationStatus>().first { it.primitiveValue == primitiveValue }
		}
	}
}
