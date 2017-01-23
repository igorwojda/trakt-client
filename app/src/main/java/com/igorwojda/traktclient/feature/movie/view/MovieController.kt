package com.igorwojda.traktclient.feature.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.mvp.conductor.controller.BaseController
import com.igorwojda.traktclient.feature.movie.MovieContract
import com.igorwojda.traktclient.feature.movie.model.MovieModel
import com.igorwojda.traktclient.feature.movie.presenter.MoviePresenter
import kotlinx.android.synthetic.main.controller_movie.view.*

//import com.igorwojda.traktclient.core.extensions.Bundle

/**
 * Created by Panel on 14.01.2017
 */
//Todo w dobr stonre MovieContract.View, a nie MovieContract.Presenter?
class MovieController(args: Bundle? = null) : BaseController<ViewGroup, Movie, MovieContract.View, MoviePresenter>(args),
		MovieContract.View {

	lateinit var movie: Movie

	constructor(movie: Movie) : this(Bundle()) {
		this.movie = movie
	}

	//Todo:
	override fun loadData(pullToRefresh: Boolean) = presenter.getMovie(movie)

	override fun setData(data: Movie) {
		movie = data

		val localView = view ?: return

		movie.imageUrl?.let {
			Glide
					.with(localView.context)
					.load(it)
					.diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.into(localView.image)
		}

		movie.title?.let { title = it }

		movie.genres?.let {
			val genres = it.joinToString(separator = " | ")
			localView.genres.text = resources?.getString(R.string.genres, genres)
		}
		movie.rating?.let { localView.ratingBar.rating = it.toFloat() }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_movie, container, false)
		return view
	}

	override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean)
			= resources?.getString(R.string.error) ?: ""

	override fun createPresenter(): MoviePresenter = MoviePresenter(movie, MovieModel()) //daggerComponent.moviePresenter()
}