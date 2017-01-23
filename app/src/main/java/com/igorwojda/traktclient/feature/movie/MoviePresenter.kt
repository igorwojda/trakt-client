package com.igorwojda.traktclient.feature.movie

import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListModel - inject?
class MoviePresenter @Inject constructor(private val movie:Movie, private val model: MovieModel) : BasePresenter<MovieContract.View>() {
	fun getMovie(movie:Movie) {
		var id = movie?.ids?.trakt

		//ToDO: id
		val subscription = model.movie(id!!)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
				{
					view?.setData(it)
					view?.showContent()
				},
				{
					view?.showError(it, false)
				})

		compositeSubscription.add(subscription)
	}

	override fun attachView(view: MovieContract.View?) {
		super.attachView(view)
		getMovie(movie)
	}
}