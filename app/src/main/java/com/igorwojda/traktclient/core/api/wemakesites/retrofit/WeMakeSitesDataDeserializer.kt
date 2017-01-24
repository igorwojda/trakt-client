package com.igorwojda.traktclient.core.api.wemakesites.retrofit

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class WeMakeSitesDataDeserializer<T : Any> :JsonDeserializer<T> {

	override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T {
		val data = json?.asJsonObject?.get("data")
		return Gson().fromJson(data, typeOfT)
	}
}