package com.igorwojda.traktclient.feature.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.extension.Bundle
import com.igorwojda.traktclient.core.mvp.conductor.controller.BaseController
import com.igorwojda.traktclient.feature.movie.presenter.MoviePresenter
import kotlinx.android.synthetic.main.controller_movie.view.*

class MovieController(args: Bundle) : BaseController<ViewGroup, Movie, MovieView, MoviePresenter>(args),
		MovieView {

	constructor(movieTraktId: String) : this(Bundle {
		putString(KEY_MOVIE_TRAKT_ID, movieTraktId)
	})

	companion object {
		private val KEY_MOVIE_TRAKT_ID = "MovieController.MOVIE_TRAKT_ID"
	}

	override fun loadData(pullToRefresh: Boolean) {
		val movieTraktId = args.getString(KEY_MOVIE_TRAKT_ID)
		presenter.loadMovie(movieTraktId)
	}

	override fun setData(movie: Movie) {

		val localView = view ?: return

		movie.imageUrl?.let {
			Glide.with(localView.context)
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
		return inflater.inflate(R.layout.controller_movie, container, false)
	}

	override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean)
			= resources?.getString(R.string.error) ?: ""

	override fun createPresenter() = applicationComponent.moviePresenter()
}