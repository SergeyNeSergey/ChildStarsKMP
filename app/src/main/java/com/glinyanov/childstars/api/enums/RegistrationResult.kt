package com.glinyanov.childstars.api.enums

import java.io.*
import com.google.gson.annotations.*

enum class RegistrationResult(val primitiveValue: Int) : Serializable {
	@SerializedName("Blocked")
	BLOCKED(0) {
		override val displayNameResourceId = "Blocked"
	},

	@SerializedName("IncorrectPassword")
	INCORRECT_PASSWORD(1) {
		override val displayNameResourceId = "IncorrectPassword"
	},

	@SerializedName("LoginAttemptsLimitExceeded")
	Registration_ATTEMPTS_LIMIT_EXCEEDED(2) {
		override val displayNameResourceId = "LoginAttemptsLimitExceeded"
	},

	@SerializedName("Timeout")
	TIMEOUT(3) {
		override val displayNameResourceId = "Timeout"
	},

	@SerializedName("AlreadyUsed")
	ALREADY_USED(4) {
		override val displayNameResourceId = "AlreadyUsed"
	},

	@SerializedName("Done")
	DONE(5) {
		override val displayNameResourceId = "Done"
	};

	abstract val displayNameResourceId: String

	companion object {
		fun fromPrimitiveValue(primitiveValue: Int): RegistrationResult? {
			return enumValues<RegistrationResult>().first { it.primitiveValue == primitiveValue }
		}
	}
}
