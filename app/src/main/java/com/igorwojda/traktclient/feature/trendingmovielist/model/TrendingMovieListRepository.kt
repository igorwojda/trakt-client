package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieApi

class TrendingMovieListRepository {
	val model = MergedMovieApi()

	//No refresh for now
	fun trending() = model.trending()
}