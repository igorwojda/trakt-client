package com.igorwojda.traktclient.feature.trendingmovielist

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.mosby.conductor.sample.tasks.TrendingMovieAdapter
import com.hannesdorfmann.mosby.conductor.viewstate.lce.MvpLceViewStateController
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.feature.movie.MovieController
import com.igorwojda.traktclient.feature.trendingmovielist.adapter.TrendingMovieAdapterDelegate

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListController : MvpLceViewStateController<RecyclerView, List<TrendingMovie>, TrendingMovieListContract.View, TrendingMovieListPresenter>(),
									TrendingMovieListContract.View {

	private lateinit var adapter: TrendingMovieAdapter

	private val navigator = TrendingMovieListPresenter(TrendingMovieListModel())

	override fun setData(data: List<TrendingMovie>) {
		adapter.items = data
		adapter.notifyDataSetChanged()
	}

	override fun loadData(pullToRefresh: Boolean) { presenter.getTrendingMovies() }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_trending_movie, container, false)

		val contentView = view.findViewById(R.id.contentView) as RecyclerView

		//ToDo: layout inflater from method param
		val delegatesManager = AdapterDelegatesManager<List<TrendingMovie>>()
				.addDelegate(TrendingMovieAdapterDelegate(activity!!.layoutInflater, { showMovie(it) }))

		adapter = TrendingMovieAdapter(delegatesManager)

		contentView.adapter = adapter
		contentView.layoutManager = LinearLayoutManager(activity)

		return view
	}

//	private fun showMovie(trendingMovie: TrendingMovie) = trendingMovie.movie?.let { navigator.showMovie(it) }
	private fun showMovie(trendingMovie: TrendingMovie) = trendingMovie.movie?.let { showMovieLocal(it) }

		//ToDo: move to com.hannesdorfmann.mosby.conductor.sample.navigation
	private fun showMovieLocal(movie:Movie) {
		val handler = HorizontalChangeHandler()

		//ToDO: Pass movie object
		router.pushController(RouterTransaction.with(MovieController(movie))
				.pushChangeHandler(handler)
				.popChangeHandler(handler)
		)
	}

	override fun createPresenter(): TrendingMovieListPresenter = TrendingMovieListPresenter(TrendingMovieListModel()) //daggerComponent.trendingMoviePresenter()

	override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String = resources?.getString(
			R.string.error) ?: ""

	override fun createViewState(): LceViewState<List<TrendingMovie>, TrendingMovieListContract.View> = RetainingLceViewState()

	override fun getData(): List<TrendingMovie> = adapter.items
}
