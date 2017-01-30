package com.igorwojda.traktclient.core.api.trakt.retrofit

import com.igorwojda.traktclient.core.api.trakt.TrakApi
import okhttp3.Interceptor
import okhttp3.Response

class TraktRequestInterceptor(private val trakt: TrakApi) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val requestBuilder = request.newBuilder()

		requestBuilder.header(TrakApi.HEADER_CONTENT_TYPE, TrakApi.CONTENT_TYPE_JSON)
		requestBuilder.header(TrakApi.HEADER_TRAKT_API_KEY, trakt.apiKey)
		requestBuilder.header(TrakApi.HEADER_TRAKT_API_VERSION, TrakApi.API_VERSION)

		// add authorization header
		if (request.header(TrakApi.HEADER_AUTHORIZATION) == null && trakt.accessToken.isNullOrEmpty()) {
			requestBuilder.header(TrakApi.HEADER_AUTHORIZATION, trakt.getAuthenticationHeaderValue())
		}
		return chain.proceed(requestBuilder.build())
	}
}
