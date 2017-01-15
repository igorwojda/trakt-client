package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import com.igorwojda.traktclient.core.model.BaseMovieModel

/**
 * Created by Panel on 14.01.2017
 */

class MovieModel {
	val baseMovieModel = BaseMovieModel()

	fun summary(traktId: String,
				extended: Extended? = Extended.FULL) = baseMovieModel.summary(traktId, extended)
}