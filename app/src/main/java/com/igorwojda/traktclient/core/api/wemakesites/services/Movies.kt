package com.igorwojda.traktclient.core.api.wemakesites.services

import com.igorwojda.traktclient.core.api.wemakesites.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface Movies {
	@GET("{resourceId}")
	fun movie(@Path("resourceId") resourcedId: String): Observable<Movie>
}
