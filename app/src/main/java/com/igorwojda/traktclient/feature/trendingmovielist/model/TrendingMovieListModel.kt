package com.igorwojda.traktclient.feature.trendingmovielist.model

import com.igorwojda.traktclient.TraktClientApplication
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListModel {

	private var trakAPI = TraktClientApplication.trakAPI
	private val weMakeSitesAPI = TraktClientApplication.weMakeSitesAPI

	fun trending(page: Int? = null,
				 limit: Int? = null,
				 extended: Extended? = null): Observable<List<TrendingMovie>> {
		return trakAPI.movies().trending(page, limit, extended)
				.flatMap {
					Observable.from(it).flatMap {
							Observable.just(it)
							.subscribeOn(Schedulers.newThread())
							.map { loadImageUrl(it) }
						}.toList()
				}
	}

	private fun loadImageUrl(trendingMovie: TrendingMovie): TrendingMovie {
		val movie = trendingMovie.movie ?: return trendingMovie
		val ids = movie.ids ?: return trendingMovie
		val imdb = ids.imdb ?: return trendingMovie

		weMakeSitesAPI.movies().movie(imdb).subscribe { movie.image = it.image }

		return trendingMovie
	}
}