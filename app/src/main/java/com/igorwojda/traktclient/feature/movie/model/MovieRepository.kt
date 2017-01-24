package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

class MovieRepository {
	val model = MergedMovieAPI()

	fun movie(traktId: String) = model.movie(traktId)
}