package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieApi

class MovieRepository {
	val model = MergedMovieApi()

	fun movie(traktId: String) = model.movie(traktId)
}