package com.igorwojda.traktclient.core.api.trakt.retrofit

import com.igorwojda.traktclient.core.api.trakt.TrakAPI
import okhttp3.Interceptor
import okhttp3.Response

class TraktRequestInterceptor(private val trakt: TrakAPI) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val requestBuilder = request.newBuilder()

		requestBuilder.header(TrakAPI.HEADER_CONTENT_TYPE, TrakAPI.CONTENT_TYPE_JSON)
		requestBuilder.header(TrakAPI.HEADER_TRAKT_API_KEY, trakt.apiKey)
		requestBuilder.header(TrakAPI.HEADER_TRAKT_API_VERSION, TrakAPI.API_VERSION)

		// add authorization header
		if (request.header(TrakAPI.HEADER_AUTHORIZATION) == null && trakt.accessToken.isNullOrEmpty()) {
			requestBuilder.header(TrakAPI.HEADER_AUTHORIZATION, trakt.getAuthenticationHeaderValue())
		}
		return chain.proceed(requestBuilder.build())
	}
}
