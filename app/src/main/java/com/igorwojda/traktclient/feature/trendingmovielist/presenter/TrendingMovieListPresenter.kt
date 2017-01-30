package com.igorwojda.traktclient.feature.trendingmovielist.presenter

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.dagger.component.DaggerApplicationComponent
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import com.igorwojda.traktclient.feature.trendingmovielist.view.TrendingMovieListView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class TrendingMovieListPresenter @Inject constructor() : BasePresenter<TrendingMovieListView>() {

	@Inject lateinit var repository: TrendingMovieListRepository
	var trendingMovies: List<TrendingMovie>? = null

	init {
		DaggerApplicationComponent.builder()
				.build()
				.inject(this)
	}

	fun loadTrendingMovies() = trendingMovies?.let { showTrendingMovies(it) } ?: loadTrendingMoviesFromModel()

	private fun loadTrendingMoviesFromModel(){
		val subscription = repository.trending()
				.subscribeOn(Schedulers.newThread())
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

		compositeSubscription.add(subscription)
	}

	private fun showTrendingMovies(list: List<TrendingMovie>){
		view?.setData(list)
		view?.showContent()
	}

	override fun attachView(view: TrendingMovieListView?) {
		super.attachView(view)
		view?.loadData(false)
	}

	fun navigateToMovie(movie: Movie) = movie?.let { navigator?.showMovie(it) }
}