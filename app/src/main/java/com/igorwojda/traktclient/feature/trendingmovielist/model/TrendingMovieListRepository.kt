package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

class TrendingMovieListRepository {
	val model = MergedMovieAPI()

	//No refresh for now
	fun trending() = model.trending()
}