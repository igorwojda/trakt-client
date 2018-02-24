package com.igorwojda.traktclient.core.net.trakt

import com.igorwojda.traktclient.core.net.trakt.entity.AccessToken
import com.igorwojda.traktclient.core.net.trakt.enum.Extended
import com.igorwojda.traktclient.core.net.trakt.retrofit.TraktAuthenticator
import com.igorwojda.traktclient.core.net.trakt.retrofit.TraktGsonHelper
import com.igorwojda.traktclient.core.net.trakt.retrofit.TraktRequestInterceptor
import com.igorwojda.traktclient.core.net.trakt.service.Authentication
import com.igorwojda.traktclient.core.net.trakt.service.Movies
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//@TestOpen
open class TrakApi(val apiKey: String, private val clientSecret: String) {
	companion object {
		const val API_URL = "https://api.trakt.tv/"
		const val HEADER_AUTHORIZATION = "Authorization"
		const val HEADER_CONTENT_TYPE = "Content-Type"
		const val CONTENT_TYPE_JSON = "application/json"
		const val HEADER_TRAKT_API_VERSION = "trakt-api-version"
		const val API_VERSION = "2"
		const val HEADER_TRAKT_API_KEY = "trakt-api-key"
		const val BEARER_PREFIX = "Bearer "
	}

	private val okHttpClient by lazy {
		OkHttpClient.Builder()
				.addNetworkInterceptor(TraktRequestInterceptor(this))
				.authenticator(TraktAuthenticator(this))
				.connectTimeout(3, TimeUnit.SECONDS)
				.build()
	}

	private val retrofit by lazy {
		Retrofit.Builder()
				.baseUrl(API_URL)
				.addConverterFactory(GsonConverterFactory.create(TraktGsonHelper.createGson()))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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


	private val movies: Movies = retrofit.create(Movies::class.java)

	fun trendingMovies() = movies.trendingMovies()

	fun summary(traktId: String, full: Extended? = null) = movies.summary(traktId, full)

	fun getAuthenticationHeaderValue(): String = "${TrakApi.BEARER_PREFIX} $accessToken"
}
