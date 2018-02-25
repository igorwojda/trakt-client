package com.igorwojda.traktclient.data.repository.trakt.retrofit

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

object TraktGsonHelper {
	private val ISO_8601_WITH_MILLIS: DateTimeFormatter = ISODateTimeFormat.dateTimeParser().withZoneUTC()

	fun createGson(): Gson = GsonBuilder()
			.registerTypeAdapter(DateTime::class.java, JsonDeserializer { json, _, _ -> ISO_8601_WITH_MILLIS.parseDateTime(json.asString) })
			.registerTypeAdapter(DateTime::class.java, JsonSerializer<DateTime> { src, _, _ -> JsonPrimitive(src.toString()) })
			.create()
}