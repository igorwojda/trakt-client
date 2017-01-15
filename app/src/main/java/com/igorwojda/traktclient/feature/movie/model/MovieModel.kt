package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

/**
 * Created by Panel on 14.01.2017
 */

class MovieModel {
	val baseMovieModel = MergedMovieAPI()

	fun summary(traktId: String,
				extended: Extended? = Extended.FULL) = baseMovieModel.summary(traktId, extended)
}