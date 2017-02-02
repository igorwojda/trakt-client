package com.igorwojda.traktclient.feature.movie.presenter

import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import com.igorwojda.traktclient.feature.movie.model.MovieRepository
import com.igorwojda.traktclient.feature.movie.view.MovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviePresenter @Inject constructor(var repository: MovieRepository) : BasePresenter<MovieView>() {

	fun loadMovie(movieTraktId: String) {
		val subscription = repository.movie(movieTraktId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							showMovie(it)

						},
						{
							view?.showError(it, false)
						})

		addSubscription(subscription)
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
