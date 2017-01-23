package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

/**
 * Created by Panel on 14.01.2017
 */

class MovieRepository {
	val model = MergedMovieAPI()

	fun movie(traktId: String) = model.movie(traktId)
}