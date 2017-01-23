package com.igorwojda.traktclient.feature.trendingmovielist

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
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

	fun getTrendingMovies() {
		subscription = model.trending()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							view?.setData(it)
							view?.showContent()
						},
						{
							view?.showError(it, false)
						}
				)
	}

	override fun attachView(view: TrendingMovieListContract.View?) {
		super.attachView(view)
		getTrendingMovies()
	}

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance) {
			subscription.unsubscribe()
		}
	}
}