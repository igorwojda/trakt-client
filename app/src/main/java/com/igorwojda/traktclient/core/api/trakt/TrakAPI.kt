package com.igorwojda.traktclient.core.api.trakt

import com.igorwojda.traktclient.core.api.trakt.services.Movies
import com.igorwojda.traktclient.core.api.trakt.entities.AccessToken
import com.igorwojda.traktclient.core.api.trakt.retrofit.TraktAuthenticator
import com.igorwojda.traktclient.core.api.trakt.retrofit.TraktGsonHelper
import com.igorwojda.traktclient.core.api.trakt.services.Authentication
import com.igorwojda.traktclient.core.api.trakt.retrofit.TraktRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Panel on 13.01.2017
 */
class TrakAPI(val apiKey: String, private val clientSecret: String) {
	companion object {
		val API_URL = "https://api.trakt.tv/"
		val HEADER_AUTHORIZATION = "Authorization"
		val HEADER_CONTENT_TYPE = "Content-Type"
		val CONTENT_TYPE_JSON = "application/json"
		val HEADER_TRAKT_API_VERSION = "trakt-api-version"
		val API_VERSION = "2"
		val HEADER_TRAKT_API_KEY = "trakt-api-key"
		val BEARER_PREFIX = "Bearer "
	}

	private val okHttpClient by lazy {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY

		OkHttpClient.Builder()
				.addNetworkInterceptor(TraktRequestInterceptor(this))
				.authenticator(TraktAuthenticator(this))
				.addInterceptor(logging)
				.build()
	}

	private val retrofit by lazy {
		Retrofit.Builder()
				.baseUrl(API_URL)
				.addConverterFactory(GsonConverterFactory.create(TraktGsonHelper.createGson()))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(okHttpClient)
				.build()
	}

	var accessToken: String? = null
	var refreshToken: String? = null

	fun refreshAccessToken(): Response<AccessToken> {
		return authentication().refreshAccessToken(
				"refresh_token",
				refreshToken,
				apiKey,
				clientSecret
		).execute()
	}

	private fun authentication(): Authentication {
		return retrofit.create(Authentication::class.java)
	}

	fun movies(): Movies {
		return retrofit.create(Movies::class.java)
	}

	fun getAuthenticationHeaderValue(): String = "${TrakAPI.BEARER_PREFIX} $accessToken"
}
