package com.igorwojda.traktclient.core.model

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import com.igorwojda.traktclient.core.model.base.BaseModel
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by Panel on 15.01.2017
 */
class BaseMovieModel : BaseModel() {

	fun trending(page: Int? = null,
				 limit: Int? = null,
				 extended: Extended? = Extended.FULL): Observable<List<TrendingMovie>> {
		return trakAPI.movies().trending(page, limit, extended)
				.flatMap {
					Observable.from(it).flatMap {
						Observable.just(it)
								.subscribeOn(Schedulers.newThread())
								.doOnNext {
									it.movie?.let {
										it.image = appendImageUrl(it)
									}
								}
					}.toList()
				}
	}

	fun summary(traktId: String,
				extended: Extended? = Extended.FULL):Observable<Movie>{
		return trakAPI.movies().summary(traktId, extended)
				.doOnNext { it.image = appendImageUrl(it) }
	}

	private fun appendImageUrl(trendingMovie: TrendingMovie): String? {
		val movie = trendingMovie.movie ?: return null
		return appendImageUrl(movie)
	}

	private fun appendImageUrl(movie: Movie): String? {
		val ids = movie.ids ?: return null
		val imdb = ids.imdb ?: return null

		var result:String? = null

		weMakeSitesAPI.movies().movie(imdb).subscribe(
				{ result = it.image }, {})

		return result
	}
}