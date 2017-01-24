package com.igorwojda.traktclient.core.api.wemakesites.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igorwojda.traktclient.core.api.wemakesites.entities.Movie

object WeMakeSitesGsonHelper {

	fun createGson(): Gson = GsonBuilder()
			.registerTypeAdapter(Movie::class.java, WeMakeSitesDataDeserializer<Movie>())
			.create()
}