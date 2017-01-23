package com.igorwojda.traktclient.feature.trendingmovielist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.mvp.conductor.controller.BaseController
import com.igorwojda.traktclient.feature.movie.view.MovieController
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import com.igorwojda.traktclient.feature.trendingmovielist.view.adapter.TrendingMovieAdapterDelegate

/**
 * Created by Panel on 14.01.2017
 */

class TrendingMovieListController(args: Bundle? = null) : BaseController<RecyclerView, List<TrendingMovie>, TrendingMovieListView, TrendingMovieListPresenter>(args),
		TrendingMovieListView {

	private lateinit var adapter: ListDelegationAdapter<List<TrendingMovie>>

	override fun setData(data: List<TrendingMovie>) {
		adapter.items = data
		adapter.notifyDataSetChanged()
	}

	override fun loadData(pullToRefresh: Boolean) { presenter.loadTrendingMovies() }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_trending_movie, container, false)

		val delegatesManager = AdapterDelegatesManager<List<TrendingMovie>>()
				.addDelegate(TrendingMovieAdapterDelegate(inflater, { showMovie(it) }))

		contentView = view.findViewById(R.id.contentView) as RecyclerView
		adapter = ListDelegationAdapter(delegatesManager)
		contentView.adapter = adapter
		contentView.layoutManager = LinearLayoutManager(activity)

		title = resources?.getString(R.string.trendngMovies) ?: ""

		return view
	}

	override fun onAttach(view: View) {
		super.onAttach(view)
		title = resources?.getString(R.string.trendngMovies) ?: ""
	}

	private fun showMovie(trendingMovie: TrendingMovie) = trendingMovie.movie?.let { presenter.navigateToMovie(it) }

		//ToDo: move to com.hannesdorfmann.mosby.conductor.sample.navigation
	private fun showMovieLocal(movieTraktId: String) {
		val handler = HorizontalChangeHandler()

		//ToDO: Pass movie object
		router.pushController(RouterTransaction.with(MovieController(movieTraktId))
				.pushChangeHandler(handler)
				.popChangeHandler(handler)
		)
	}

	override fun createPresenter(): TrendingMovieListPresenter = TrendingMovieListPresenter(TrendingMovieListRepository()) //daggerComponent.trendingMoviePresenter()

	override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String = resources?.getString(
			R.string.error) ?: ""
}
