package com.igorwojda.traktclient.core.api.merged

import com.igorwojda.traktclient.TraktClientApplication
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by Panel on 15.01.2017
 */
class MergedMovieAPI {

	private var trakAPI = TraktClientApplication.trakAPI
	private val weMakeSitesAPI = TraktClientApplication.weMakeSitesAPI
	private val diskCache = TraktClientApplication.diskCache

	fun trending(): Observable<List<TrendingMovie>> {
		val network = trakAPI.movies().trending(extended = Extended.FULL)
				.flatMap {
					Observable.from(it).flatMap {
						Observable.just(it)
								.subscribeOn(Schedulers.newThread())
								.doOnNext {
									it.movie?.let {
										it.imageUrl = getImageUrl(it)
									}
								}
								.doOnNext { diskCache.save(it) }
					}.toList()
				}

		val disk = diskCache.getTrendingMovies()

		return network.onErrorResumeNext { disk }
	}

	fun movie(traktId: String): Observable<Movie> {
		val network = trakAPI.movies().summary(traktId, Extended.FULL)
				.doOnNext {
					it.imageUrl = getImageUrl(it)
					diskCache.save(it)
				}.subscribeOn(Schedulers.newThread())

		val disk = diskCache.getMovies()
				.flatMap {
					Observable.from(it)
							.first { it?.ids?.trakt == traktId }
				}

		return disk.onErrorResumeNext { network }
	}

	private fun getImageUrl(movie: Movie): String? {
		val ids = movie.ids ?: return null
		val imdb = ids.imdb ?: return null

		var url: String? = null

		weMakeSitesAPI.movies().movie(imdb).subscribe(
				{ url = it.image }, {})

		return url
	}
}