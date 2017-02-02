package com.igorwojda.traktclient.core.net.trakt.retrofit

import com.igorwojda.traktclient.core.net.trakt.TrakApi
import com.igorwojda.traktclient.core.net.trakt.extension.numPriorResponses
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TraktAuthenticator(val trakt: TrakApi) : Authenticator {

	@Throws(IOException::class)
	override fun authenticate(route: Route, response: Response): Request? {
		return handleAuthenticate(response, trakt)
	}

	companion object {
		fun handleAuthenticate(response: Response, trakt: TrakApi): Request? {

			//failed 3 times
			if (response.numPriorResponses >= 3)
				return null

			if (trakt.refreshToken.isNullOrEmpty())
				return null

			val refreshResponse = trakt.refreshAccessToken()

			if (!refreshResponse.isSuccessful)
				return null

			trakt.accessToken = refreshResponse.body().access_token
			trakt.refreshToken = refreshResponse.body().refresh_token

			// retry request
			return response.request().newBuilder()
					.header(TrakApi.HEADER_AUTHORIZATION, trakt.getAuthenticationHeaderValue())
					.build()
		}
	}

}
