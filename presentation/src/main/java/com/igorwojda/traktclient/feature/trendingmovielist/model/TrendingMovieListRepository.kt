package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import javax.inject.Inject

class TrendingMovieListRepository @Inject constructor(private val mergedMovieService: MergedMovieApi) {
	//No refresh for now
	fun trending() = mergedMovieService.trendingMovies()
}