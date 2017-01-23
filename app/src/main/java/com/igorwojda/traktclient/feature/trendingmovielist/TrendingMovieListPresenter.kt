package com.igorwojda.traktclient.feature.trendingmovielist

import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import net.vrallev.android.cat.Cat
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Panel on 22.01.2017
 */
//Todo: change TrendingMovieListModel - inject?
class TrendingMovieListPresenter @Inject constructor(private val model: TrendingMovieListModel) : BasePresenter<TrendingMovieListContract.View>() {
	private var trendingMovies: List<TrendingMovie>? = null

	fun loadTrendingMovies() {
		trendingMovies?.let { showTrendingMovies(it) } ?: loadTrendingMoviesFromModel()
	}

	private fun loadTrendingMoviesFromModel(){
		Cat.d("loadTrendingMoviesFromModel")
		val subscription = model.trending()
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
		Cat.d("showTrendingMovies")
		view?.setData(list)
		view?.showContent()
	}

	override fun attachView(view: TrendingMovieListContract.View?) {
		super.attachView(view)

		loadTrendingMovies()
	}
}