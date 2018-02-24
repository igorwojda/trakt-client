package com.igorwojda.traktclient.core.net.trakt.service

import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie
import com.igorwojda.traktclient.core.net.trakt.enum.Extended
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movies {
	@GET("movies/trending")
	fun trendingMovies(
			@Query("page") page: Int? = null,
			@Query("limit") limit: Int? = null,
			@Query(value = "extended") extended: Extended? = null
	): Single<List<TrendingMovie>>

	@GET("movies/{id}")
	fun summary(
			@Path("id") movieId: String,
			@Query(value = "extended") extended: Extended? = null
	): Single<Movie>
}
