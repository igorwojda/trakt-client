package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListModel {
	val model = MergedMovieAPI()

	//No refresh for now
	fun trending() = model.trending()
}