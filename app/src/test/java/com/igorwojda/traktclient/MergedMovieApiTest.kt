package com.igorwojda.traktclient

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import com.igorwojda.traktclient.core.net.trakt.TrakApi
import com.igorwojda.traktclient.core.net.trakt.entity.MovieIds
import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie
import com.igorwojda.traktclient.core.net.trakt.enum.Extended
import com.igorwojda.traktclient.core.net.wemakesites.WeMakeSitesApi
import com.igorwojda.traktclient.core.rule.ImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import com.igorwojda.traktclient.core.net.trakt.entity.Movie as TraktMovie
import com.igorwojda.traktclient.core.net.wemakesites.entity.Movie as WeMakeSitesMovie

class MergedMovieApiTest {

	@JvmField @Rule var rule: MockitoRule = MockitoJUnit.rule()
	@JvmField @Rule var immediateSchedulerRule = ImmediateSchedulerRule()

	@Mock lateinit var traktApi: TrakApi
	@Mock lateinit var weMakeSitesApi: WeMakeSitesApi

	@InjectMocks lateinit var mergedMovieApi: MergedMovieApi

	@Test
	fun testMovieSubscribe_merge_imageUrl() {
		/* Given */
		`when`(traktApi.summary("traktId-1", Extended.FULL))
				.thenReturn(Single.just(getFakeTraktMovie("traktId-1")))

		`when`(weMakeSitesApi.movie("imdbId-1"))
				.thenAnswer { getFakeWeMakeSitesMovie(it.getArgument(0)) }

		/* When */
		val testObserver = mergedMovieApi.movie("traktId-1").test()
		testObserver.awaitTerminalEvent()

		/* Then */
		testObserver.apply {
			assertNoErrors()
			assertComplete()

			assertValueCount(1)
			assertValue { !it.imageUrl.isNullOrEmpty() }
		}
	}

	@Test
	fun testTrendingMoviesSubscribe_merge_imageUrl() {
		/* Given */

		`when`(traktApi.trendingMovies())
				.thenReturn(Single.just(getFakeTrendingMovies()))

		`when`(weMakeSitesApi.movie(anyString()))
				.thenAnswer { getFakeWeMakeSitesMovie(it.getArgument(0)) }

		/* When */
		val testObserver = mergedMovieApi.trendingMovies().test()
		testObserver.awaitTerminalEvent()

		/* Then */
		testObserver.apply {
			assertNoErrors()
			assertComplete()

			assertValueCount(1)
			assertValue { it.size == 3 }

			assertValue {
				val urlCount = Observable.fromIterable(it)
						.map { it.movie?.imageUrl }
						.toList()
						.blockingGet()
						.count { !it.isNullOrBlank() }

				urlCount == it.size
			}
		}
	}

	fun getFakeTrendingMovies(): List<TrendingMovie> {
		val list: MutableList<TrendingMovie> = arrayListOf()

		(1..3).forEach {
			list.add(TrendingMovie().apply {
				watchers = it
				movie = TraktMovie().apply {
					title = "Title"
					released = "2011"
					ids = MovieIds().apply { imdb = "imdbId-$it" }
				}
			})
		}

		return list
	}

	fun getFakeTraktMovie(traktId: String): TraktMovie {
		val imdbId = "imdbId-${traktId.split("-").last()}"

		val movie = TraktMovie().apply {
			title = "Title"
			released = "2011"
			ids = MovieIds().apply {
				imdb = imdbId
				trakt = traktId
			}
		}

		return movie
	}

	fun getFakeWeMakeSitesMovie(imdbId: String = "none"): Single<WeMakeSitesMovie> {
		val movie = WeMakeSitesMovie()
		movie.image = "http://test.com/image.jpg"
		movie.id = imdbId
		return Single.just(movie)
	}
}