package com.igorwojda.traktclient.feature.movie.presenter

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.dagger.component.DaggerApplicationComponent
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.feature.movie.model.MovieRepository
import com.igorwojda.traktclient.feature.movie.view.MovieView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MoviePresenter : BasePresenter<MovieView>() {

	@Inject lateinit var repository: MovieRepository

	init {
		DaggerApplicationComponent.builder()
				.build()
				.inject(this)
	}

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