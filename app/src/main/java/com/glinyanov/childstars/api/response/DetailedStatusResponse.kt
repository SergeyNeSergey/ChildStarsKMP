package com.glinyanov.childstars.api.response

import kotlinx.serialization.Serializable

@Serializable
open class DetailedStatusResponse<TStatus> {
	var detailedStatus: TStatus? = null
	var detailedStatusDescription: String? = null

	constructor() : super()

	constructor(detailedStatus: TStatus?, detailedStatusDescription: String?) {
		this.detailedStatus = detailedStatus
		this.detailedStatusDescription = detailedStatusDescription
	}
}
