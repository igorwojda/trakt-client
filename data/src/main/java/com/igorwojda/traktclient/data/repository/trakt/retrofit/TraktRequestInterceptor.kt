package com.igorwojda.traktclient.data.repository.trakt.retrofit

import com.igorwojda.traktclient.data.repository.trakt.TraktRemoteRepository
import okhttp3.Interceptor
import okhttp3.Response

class TraktRequestInterceptor : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val requestBuilder = request.newBuilder()

		requestBuilder.header(TraktRemoteRepository.HEADER_CONTENT_TYPE, TraktRemoteRepository.CONTENT_TYPE_JSON)
		requestBuilder.header(TraktRemoteRepository.HEADER_TRAKT_API_KEY, TraktRemoteRepository.apiKey)
		requestBuilder.header(TraktRemoteRepository.HEADER_TRAKT_API_VERSION, TraktRemoteRepository.API_VERSION)

		// add authorization header
		if (request.header(TraktRemoteRepository.HEADER_AUTHORIZATION) == null && TraktRemoteRepository.accessToken.isNullOrEmpty()) {
			requestBuilder.header(TraktRemoteRepository.HEADER_AUTHORIZATION, getAuthenticationHeaderValue())
		}
		return chain.proceed(requestBuilder.build())
	}
}
