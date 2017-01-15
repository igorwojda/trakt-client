package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI
import com.igorwojda.traktclient.core.api.trakt.enums.Extended

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListModel {
	val baseMovieModel = MergedMovieAPI()

	fun trending(page: Int? = null,
				 limit: Int? = null,
				 extended: Extended? = null) = baseMovieModel.trending(page, limit, extended)
}