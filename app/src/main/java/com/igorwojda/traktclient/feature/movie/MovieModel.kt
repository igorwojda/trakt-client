package com.igorwojda.traktclient.feature.movie

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

/**
 * Created by Panel on 14.01.2017
 */

class MovieModel {
	val model = MergedMovieAPI()

	fun movie(traktId: String) = model.movie(traktId)
}