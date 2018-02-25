package com.igorwojda.traktclient.data.repository.trakt.service

import com.igorwojda.traktclient.data.repository.trakt.enum.Extended
import com.igorwojda.traktclient.domain.entity.Movie
import com.igorwojda.traktclient.domain.entity.TrendingMovie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TraktRetrofitService {
    @GET("movies/trending")
    fun trendingMovies(
            @Query("page") page: Int? = null,
            @Query("limit") limit: Int? = null,
            @Query(value = "extended") extended: Extended? = Extended.FULL
    ): Single<List<TrendingMovie>>

    @GET("movies/{id}")
    fun summary(
            @Path("id") movieId: String,
            @Query(value = "extended") extended: Extended? = Extended.FULL
    ): Single<Movie>
}
