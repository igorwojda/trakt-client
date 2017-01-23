package com.igorwojda.traktclient.feature.trendingmovielist.presenter

import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.TrendingMovieListContract
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListRepository - inject?
class TrendingMovieListPresenter @Inject constructor(private val repository: TrendingMovieListRepository) : BasePresenter<TrendingMovieListContract.View>() {
	private var trendingMovies: List<TrendingMovie>? = null

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

	override fun attachView(view: TrendingMovieListContract.View?) {
		super.attachView(view)
		view?.loadData(false)
	}
}