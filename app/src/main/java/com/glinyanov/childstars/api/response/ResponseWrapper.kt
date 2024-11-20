package com.glinyanov.childstars.api.response

import com.glinyanov.childstars.api.enums.OperationStatus
import kotlinx.serialization.Serializable

@Serializable
class ResponseWrapper<T> {
	var operationStatus: OperationStatus = OperationStatus.SUCCESS
	var responseData: T? = null

	constructor() : super()

	constructor(operationStatus: OperationStatus, responseData: T?) {
		this.operationStatus = operationStatus
		this.responseData = responseData
	}
}
