package com.igorwojda.traktclient.core.net.trakt.extension

import okhttp3.Response

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