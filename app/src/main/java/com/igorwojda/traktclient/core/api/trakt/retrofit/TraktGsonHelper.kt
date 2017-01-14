package com.igorwojda.traktclient.core.api.trakt.retrofit

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

/**
 * Created by Panel on 13.01.2017
 */
object TraktGsonHelper {
	private val ISO_8601_WITH_MILLIS: DateTimeFormatter = ISODateTimeFormat.dateTimeParser().withZoneUTC()

	fun createGson(): Gson = GsonBuilder()
			.registerTypeAdapter(DateTime::class.java, JsonDeserializer { json, typeOfT, context -> ISO_8601_WITH_MILLIS.parseDateTime(json.asString) })
			.registerTypeAdapter(DateTime::class.java, JsonSerializer<DateTime> { src, typeOfSrc, context -> JsonPrimitive(src.toString()) })
			.create()
}