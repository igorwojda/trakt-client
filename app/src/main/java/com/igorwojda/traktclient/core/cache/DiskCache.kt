package com.igorwojda.traktclient.core.cache

import au.com.gridstone.rxstore.StoreProvider
import au.com.gridstone.rxstore.converters.MoshiConverter
import au.com.gridstone.rxstore.listStore
import com.igorwojda.traktclient.TraktClientApplication
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie

class DiskCache {
	init {

	}

	private val CACHE_SIZE = 100

	private val storeProvider by lazy { StoreProvider.withContext(TraktClientApplication.instance).inDir("storeProvider").using(MoshiConverter()) }
	private var movieStore = storeProvider.listStore<Movie>("movie")
	private val trendingMovieStore = storeProvider.listStore<TrendingMovie>("trendingMovie")

	fun save(movie: Movie) {
		movieStore.blocking.contains(movie)

		if (movieStore.blocking.size >= CACHE_SIZE)
			movieStore.removeFromList(0)

		movieStore.addToList(movie)
	}

	fun getMovies() = movieStore.asObservable()

	fun save(trendingMovie: TrendingMovie) {
		if (trendingMovieStore.blocking.size >= CACHE_SIZE)
			trendingMovieStore.removeFromList(0)

		trendingMovieStore.addToList(trendingMovie)

		trendingMovie.movie?.let {
			save(it)
		}
	}

	fun getTrendingMovies() = trendingMovieStore.asObservable()
}