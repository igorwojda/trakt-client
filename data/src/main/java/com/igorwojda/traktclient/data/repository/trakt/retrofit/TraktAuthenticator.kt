package com.igorwojda.traktclient.data.repository.trakt.retrofit

import com.igorwojda.traktclient.data.repository.trakt.TraktRemoteRepository
import com.igorwojda.traktclient.data.repository.trakt.extension.numPriorResponses
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TraktAuthenticator() : Authenticator {

    override fun authenticate(route: Route, response: Response): Request? {
        return handleAuthenticate(response)
    }

    companion object {
        fun handleAuthenticate(response: Response): Request? {

            //failed 3 times
            if (response.numPriorResponses >= 3)
                return null

            if (TraktRemoteRepository.refreshToken.isNullOrEmpty())
                return null

            val refreshResponse = TraktRemoteRepository.refreshAccessToken() ?: return null

            if (!refreshResponse.isSuccessful)
                return null

            TraktRemoteRepository.accessToken = refreshResponse.body()?.access_token
            TraktRemoteRepository.refreshToken = refreshResponse.body()?.refresh_token

            // retry request
            return response.request().newBuilder()
                    .header(TraktRemoteRepository.HEADER_AUTHORIZATION, TraktRemoteRepository.getAuthenticationHeaderValue())
                    .build()
        }
    }
}
