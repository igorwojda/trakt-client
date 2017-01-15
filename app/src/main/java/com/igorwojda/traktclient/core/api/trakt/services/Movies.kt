package com.igorwojda.traktclient.core.api.trakt.services

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.api.trakt.enums.Extended
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Panel on 13.01.2017
 */
interface Movies {
	@GET("movies/trending")
	fun trending(
			@Query("page") page: Int? = null,
			@Query("limit") limit: Int? = null,
			@Query(value = "extended", encoded = true) extended: Extended? = Extended.FULL
	): Observable<List<TrendingMovie>>

	@GET("movies/{id}")
	fun summary(
			@Path("id") movieId: String,
			@Query(value = "extended", encoded = true) extended: Extended? = Extended.FULL
	): Observable<Movie>
}
