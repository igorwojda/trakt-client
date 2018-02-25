package com.igorwojda.traktclient.domain.repository

import com.igorwojda.traktclient.domain.entity.Movie
import com.igorwojda.traktclient.domain.entity.TrendingMovie
import io.reactivex.Single

interface TraktRepository {
    fun getTrendingMovieList(): Single<List<TrendingMovie>>
    fun getMovie(traktMovieId: String): Single<Movie>
}
