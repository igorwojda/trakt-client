package com.igorwojda.traktclient.data.repository.trakt

import com.igorwojda.traktclient.data.repository.trakt.service.TraktRetrofitService
import com.igorwojda.traktclient.domain.entity.AccessToken
import com.igorwojda.traktclient.domain.entity.Movie
import com.igorwojda.traktclient.domain.entity.TrendingMovie
import com.igorwojda.traktclient.domain.repository.TraktRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TraktRemoteRepository @Inject constructor(private val traktRetrofitService: TraktRetrofitService,
                                                private val retrofit: Retrofit) : TraktRepository {
    companion object {
        const val API_URL = "https://api.trakt.tv/"
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val CONTENT_TYPE_JSON = "application/json"
        const val HEADER_TRAKT_API_VERSION = "trakt-api-version"
        const val API_VERSION = "2"
        const val HEADER_TRAKT_API_KEY = "trakt-api-key"
        const val BEARER_PREFIX = "Bearer "

        var accessToken: String? = null
        var refreshToken: String? = null
        val apiKey = "0e7e55d561c7e688868a5ea7d2c82b17e59fde95fbc2437e809b1449850d4162"
        const val clientSecret = "acac2f8984eff126f39d2aa53ce08366733fda1013cc6e5e09699dadbbab517f"

        fun getAuthenticationHeaderValue(): String = "${TraktRemoteRepository.BEARER_PREFIX} ${TraktRemoteRepository.accessToken}"

        fun refreshAccessToken(): Response<AccessToken> {
//            return retrofit.create(Authentication::class.java).refreshAccessToken(
//                    "refresh_token",
//                    refreshToken,
//                    apiKey,
//                    clientSecret
//            ).execute()
        }

    }

    override fun getTrendingMovieList(): Single<List<TrendingMovie>> =
            traktRetrofitService.trendingMovies()
                    .subscribeOn(Schedulers.io())

    override fun getMovie(traktMovieId: String): Single<Movie> =
            traktRetrofitService.summary(traktMovieId)
                    .subscribeOn(Schedulers.io())
}