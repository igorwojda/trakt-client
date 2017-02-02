package com.igorwojda.traktclient.feature.movie.model

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val mergedMovieService: MergedMovieApi) {
	fun movie(traktId: String) = mergedMovieService.movie(traktId)
}