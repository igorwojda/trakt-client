package com.igorwojda.traktclient.feature.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hannesdorfmann.mosby.conductor.viewstate.lce.MvpLceViewStateController
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import kotlinx.android.synthetic.main.controller_movie.view.*

//import com.igorwojda.traktclient.core.extensions.Bundle

/**
 * Created by Panel on 14.01.2017
 */
//Todo w dobr stonre MovieContract.View, a nie MovieContract.Presenter?
class MovieController(args: Bundle) : MvpLceViewStateController<ViewGroup, Movie, MovieContract.View, MoviePresenter>(args),
		MovieContract.View {

	var movie: Movie? = null

	constructor(movie: Movie) : this(Bundle()) {
		this.movie = movie
	}

	//Todo:
	override fun loadData(pullToRefresh: Boolean) = presenter.getMovie(movie!!)

	override fun setData(data: Movie) {
		movie = data

		val movie: Movie = movie ?: return
		val localView = view ?: return

		movie.imageUrl?.let {
			Glide
					.with(localView.context)
					.load(it)
					.diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.into(localView.image)
		}

//		movie.title?.let { title = it }

		movie.genres?.let {
			val genres = it.joinToString(separator = " | ")
			localView.genres.text = resources?.getString(R.string.genres, genres)
		}
		movie.rating?.let { localView.ratingBar.rating = it.toFloat() }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		return inflater.inflate(R.layout.controller_movie, container, false)
	}

	override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean)
			= resources?.getString(R.string.error) ?: ""

	override fun createPresenter(): MoviePresenter = MoviePresenter(MovieModel()) //daggerComponent.moviePresenter()

	override fun getData() = movie

	override fun createViewState(): LceViewState<Movie, MovieContract.View> = RetainingLceViewState()
}