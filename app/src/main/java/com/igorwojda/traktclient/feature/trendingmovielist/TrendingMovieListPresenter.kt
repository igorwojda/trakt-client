package com.igorwojda.traktclient.feature.trendingmovielist

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListModel - inject?
class TrendingMovieListPresenter @Inject constructor(private val model: TrendingMovieListModel) : MvpBasePresenter<TrendingMovieListContract.View>() {

	private lateinit var subscription: Subscription

	private var trendingMovies: List<TrendingMovie>? = null

	fun getTrendingMovies() {

		trendingMovies?.let {
			showTrendingMovies(it)
			return
		}

		subscription = model.trending()
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
	}

	private fun showTrendingMovies(list: List<TrendingMovie>){
		view?.setData(list)
		view?.showContent()
	}

	override fun attachView(view: TrendingMovieListContract.View?) {
		super.attachView(view)


		if(trendingMovies == null)
			getTrendingMovies()
		else
			trendingMovies?.let { showTrendingMovies(it) }
	}

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance) {
			subscription.unsubscribe()
		}
	}
}