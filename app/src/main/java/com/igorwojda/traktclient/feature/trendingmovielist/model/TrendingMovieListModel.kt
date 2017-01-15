package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import com.igorwojda.traktclient.core.model.BaseMovieModel

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListModel {
	val baseMovieModel = BaseMovieModel()

	fun trending(page: Int? = null,
				 limit: Int? = null,
				 extended: Extended? = null) = baseMovieModel.trending(page, limit, extended)
}