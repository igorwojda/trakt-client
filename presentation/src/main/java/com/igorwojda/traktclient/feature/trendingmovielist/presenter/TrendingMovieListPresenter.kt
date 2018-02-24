package com.igorwojda.traktclient.feature.trendingmovielist.presenter

import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import com.igorwojda.traktclient.feature.trendingmovielist.view.TrendingMovieListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingMovieListPresenter @Inject constructor(var repository: TrendingMovieListRepository) : BasePresenter<TrendingMovieListView>() {

	private var trendingMovies: List<TrendingMovie>? = null

	fun loadTrendingMovies() = trendingMovies?.let { showTrendingMovies(it) } ?: loadTrendingMoviesFromRepository()

	private fun loadTrendingMoviesFromRepository() {
		val subscription = repository.trending()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							showTrendingMovies(it)
							trendingMovies = it
						},
						{
							view?.showError(it, false)
						}
				)

		addSubscription(subscription)
	}

	private fun showTrendingMovies(list: List<TrendingMovie>) {
		view?.setData(list)
		view?.showContent()
	}

	override fun attachView(view: TrendingMovieListView?) {
		super.attachView(view)
		view?.loadData(false)
	}

	fun navigateToMovie(movie: Movie) = movie.let { navigator?.showMovie(it) }
}