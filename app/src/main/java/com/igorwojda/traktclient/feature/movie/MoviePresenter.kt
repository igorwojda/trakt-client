package com.igorwojda.traktclient.feature.movie

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListModel - inject?
class MoviePresenter @Inject constructor(private val movie:Movie, private val model: MovieModel) : MvpBasePresenter<MovieContract.View>() {

	//Todo: subscriptio
	private lateinit var subscription: Subscription

	fun getMovie(movie:Movie) {
		var id = movie?.ids?.trakt

		subscription = model.movie(id!!)
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
	}

	override fun attachView(view: MovieContract.View?) {
		super.attachView(view)
		getMovie(movie)
	}

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance) {
			subscription.unsubscribe()
		}
	}
}