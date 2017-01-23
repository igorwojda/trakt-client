package com.igorwojda.traktclient.feature.trendingmovielist

import com.igorwojda.traktclient.core.api.merged.MergedMovieAPI
import javax.inject.Inject

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListModel @Inject constructor() {
	val model = MergedMovieAPI()

	//No refresh for now
	fun trending() = model.trending()
}