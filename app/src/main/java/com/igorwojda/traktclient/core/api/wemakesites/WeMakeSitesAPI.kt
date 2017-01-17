package com.igorwojda.traktclient.core.api.wemakesites

import com.igorwojda.traktclient.core.api.wemakesites.retrofit.WeMakeSitesGsonHelper
import com.igorwojda.traktclient.core.api.wemakesites.retrofit.WeMakeSitesRequestInterceptor
import com.igorwojda.traktclient.core.api.wemakesites.services.Movies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Panel on 13.01.2017
 */
class WeMakeSitesAPI(private val apiKey: String) {

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
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(okHttpClient)
				.build()
	}

	fun movies(): Movies {
		return retrofit.create(Movies::class.java)
	}
}