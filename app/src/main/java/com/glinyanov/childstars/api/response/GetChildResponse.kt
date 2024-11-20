package com.glinyanov.childstars.api.response

import com.glinyanov.childstars.api.request.ExposedUser
import kotlinx.serialization.Serializable

@Serializable
class GetChildResponse : DetailedStatusResponse<EmptyResponse> {
	var childList: List<ExposedUser>? = null

	constructor() : super()

	constructor(childList: List<ExposedUser>?, detailedStatus: EmptyResponse?, detailedStatusDescription: String?) : super(detailedStatus, detailedStatusDescription) {
		this.childList = childList
	}
}
