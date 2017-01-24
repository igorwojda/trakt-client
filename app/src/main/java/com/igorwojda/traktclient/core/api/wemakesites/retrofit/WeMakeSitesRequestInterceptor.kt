package com.igorwojda.traktclient.core.api.wemakesites.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class WeMakeSitesRequestInterceptor(private val apiKey:String) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val originalHttpUrl = request.url()

		val url = originalHttpUrl.newBuilder()
				.addQueryParameter("api_key", apiKey)
				.build()

		val requestBuilder = request.newBuilder()
				.url(url)

		return chain.proceed(requestBuilder.build())
	}
}
