package com.igorwojda.traktclient.feature.trendingmovielist

import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(JUnit4::class)
class TrendingMovieListPresenterTest {
	@Inject lateinit var trendingMovieListPresenter: TrendingMovieListPresenter

	@Before
	fun setUp() {
		trendingMovieListPresenter = TrendingMovieListPresenter()
	}

	@Test
	fun populatesRecyclerViewWithListOfTrendingMovies() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}