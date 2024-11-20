package com.glinyanov.childstars.api.response

import com.glinyanov.childstars.api.enums.RegistrationResult
import kotlinx.serialization.Serializable

@Serializable
class RegistrationResponse : DetailedStatusResponse<RegistrationResult> {
	var id: Int = -1

	constructor() : super()

	constructor(id: Int, detailedStatus: RegistrationResult?, detailedStatusDescription: String?) : super(detailedStatus, detailedStatusDescription) {
		this.id = id
	}
}
