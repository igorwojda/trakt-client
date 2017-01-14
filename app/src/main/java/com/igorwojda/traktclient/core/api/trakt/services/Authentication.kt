package com.igorwojda.traktclient.core.api.trakt.services

import com.igorwojda.traktclient.core.api.trakt.entities.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Panel on 13.01.2017
 */
interface Authentication {

	@FormUrlEncoded
	@POST("/oauth/token")
	fun refreshAccessToken(
			@Field("grant_type") grantType: String,
			@Field("refresh_token") refreshToken: String?,
			@Field("client_id") clientId: String,
			@Field("client_secret") clientSecret: String
	): Call<AccessToken>

}
