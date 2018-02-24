package com.igorwojda.traktclient.core.net.merged

import com.igorwojda.traktclient.core.net.trakt.TrakApi
import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie
import com.igorwojda.traktclient.core.net.trakt.enum.Extended
import com.igorwojda.traktclient.core.net.wemakesites.WeMakeSitesApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MergedMovieApi @Inject constructor(private val trakAPI: TrakApi,
										 private val weMakeSitesAPI: WeMakeSitesApi) {

	fun trendingMovies(): Single<MutableList<TrendingMovie>> =
            trakAPI.trendingMovies()
            .flattenAsObservable { it }
            .flatMapSingle { loadImageUrl(it) }
            .toList()

	fun movie(traktId: String): Single<Movie> =
            trakAPI.summary(traktId, Extended.FULL)
            .flatMap { loadImageUrl(it) }

	private fun loadImageUrl(trendingMovie: TrendingMovie): Single<TrendingMovie> {
		return trendingMovie.movie?.let { loadImageUrl(it).map { trendingMovie } } ?: Single.just(trendingMovie)
	}

	private fun loadImageUrl(movie: Movie): Single<Movie> {
		val imdbId = movie.ids?.imdb ?: return Single.just(movie)

		return weMakeSitesAPI.movie(imdbId)
				.subscribeOn(Schedulers.io())
				.map { it.image }
				.map { movie.imageUrl = it }
				.map { movie }
				.onErrorReturn { movie }
	}
}