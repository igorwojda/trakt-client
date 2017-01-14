package com.igorwojda.traktclient.core.api.trakt.entities

data class AccessToken (
	var access_token: String? = null,
	var refresh_token: String? = null
)
