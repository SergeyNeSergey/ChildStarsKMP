package com.glinyanov.childstars.api.response

import kotlinx.serialization.Serializable

@Serializable
class OtpResponse : DetailedStatusResponse<EmptyResponse> {
	var otp: String = ""

	constructor() : super()

	constructor(otp: String, detailedStatus: EmptyResponse?, detailedStatusDescription: String?) : super(detailedStatus, detailedStatusDescription) {
		this.otp = otp
	}
}
