package com.igorwojda.traktclient.core.net.wemakesites.service

import com.igorwojda.traktclient.core.net.fanarttv.entity.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Movies {
	@GET("{resourceId}")
	fun movie(@Path("resourceId") resourcedId: String): Single<Movie>
}
