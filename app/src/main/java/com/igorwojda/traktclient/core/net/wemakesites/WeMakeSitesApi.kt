package com.igorwojda.traktclient.core.net.wemakesites

import com.igorwojda.traktclient.core.net.wemakesites.entity.Movie
import com.igorwojda.traktclient.core.net.wemakesites.retrofit.WeMakeSitesGsonHelper
import com.igorwojda.traktclient.core.net.wemakesites.retrofit.WeMakeSitesRequestInterceptor
import com.igorwojda.traktclient.core.net.wemakesites.service.Movies
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeMakeSitesApi(private val apiKey: String) {

	companion object {
		val API_URL = "http://imdb.wemakesites.net/api/"
	}

	private val okHttpClient by lazy {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY

		OkHttpClient.Builder()
				.addNetworkInterceptor(WeMakeSitesRequestInterceptor(apiKey))
//				.addInterceptor(logging)
				.connectTimeout(3, TimeUnit.SECONDS)
				.build()
	}

	private val retrofit by lazy {
		Retrofit.Builder()
				.baseUrl(API_URL)
				.addConverterFactory(GsonConverterFactory.create(WeMakeSitesGsonHelper.createGson()))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.build()
	}

	private val movies: Movies = retrofit.create(Movies::class.java)

	fun movie(resourceId: String): Single<Movie> {
		return movies.movie(resourceId)
	}
}