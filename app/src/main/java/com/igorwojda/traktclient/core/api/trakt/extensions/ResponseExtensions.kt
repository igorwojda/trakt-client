package com.igorwojda.traktclient.core.api.trakt.extensions

import okhttp3.Response

/**
 * Created by Panel on 13.01.2017
 */
val Response.numPriorResponses:Int
	get() {
		var result = 1
		var response = this

		while (response.priorResponse() != null) {
			response = response.priorResponse()
			result++
		}

		return result
	}