package com.glinyanov.childstars.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : JsonDeserializer<Date> {

    companion object {
        private const val SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {

        val df = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
        return try {
            df.parse(json.asString)
        } catch (e: ParseException) {
            return null
        }
    }
}