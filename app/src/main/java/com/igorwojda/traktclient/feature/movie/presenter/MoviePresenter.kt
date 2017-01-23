package com.igorwojda.traktclient.feature.movie.presenter

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.feature.movie.model.MovieRepository
import com.igorwojda.traktclient.feature.movie.view.MovieView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListRepository - inject?
class MoviePresenter : BasePresenter<MovieView>() {

	private val repository: MovieRepository = MovieRepository()

	fun loadMovie(movieTraktId: String) {
		val subscription = repository.movie(movieTraktId)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							showMovie(it)

						},
						{
							view?.showError(it, false)
						})

		compositeSubscription.add(subscription)
	}

	private fun showMovie(movie: Movie) {
		view?.setData(movie)
		view?.showContent()
	}

	override fun attachView(view: MovieView?) {
		super.attachView(view)
		view?.loadData(false)
	}
}