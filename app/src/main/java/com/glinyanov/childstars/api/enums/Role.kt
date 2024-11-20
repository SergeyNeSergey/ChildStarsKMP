package com.glinyanov.childstars.api.enums

import java.io.*
import com.google.gson.annotations.*

enum class Role(val primitiveValue: Int) : Serializable {
	@SerializedName("Parent")
	PARENT(0),

	@SerializedName("Child")
	CHILD(1),

	@SerializedName("Empty")
	EMPTY(-1);
}
