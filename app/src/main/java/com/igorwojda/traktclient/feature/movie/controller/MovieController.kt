package com.igorwojda.traktclient.feature.movie.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.controllers.base.BaseController
import com.igorwojda.traktclient.core.extension.Bundle
import com.igorwojda.traktclient.feature.movie.model.MovieModel
import kotlinx.android.synthetic.main.controller_movie.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

//import com.igorwojda.traktclient.core.extensions.Bundle

/**
 * Created by Panel on 14.01.2017
 */
class MovieController(args: Bundle) : BaseController(args) {

	private val model = MovieModel()

	constructor(movieTraktId: String) : this(Bundle {
		putString(KEY_MOVIE_TRAKT_ID, movieTraktId)
	})

	companion object {
		private val KEY_MOVIE_TRAKT_ID = "MovieController.KEY_MOVIE_TRAKT_ID"
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_movie, container, false)

		var movieTraktId = args.getString(KEY_MOVIE_TRAKT_ID)
		model.summary(movieTraktId)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							updateView(it)
						},
						{
							Log.e("Error", it.message)
						}
				)

		return view
	}

	private fun updateView(movie: Movie) {
		var localView = view ?: return

		movie.image?.let {
			Glide
					.with( localView.context )
					.load( it )
					.into( localView.controller_movie_image )
		}

		movie.title?.let { title = it}

		val genresLabel = resources?.getText(R.string.genres)
		movie.genres?.let { localView.controller_movie_genres.text = "$genresLabel: ${it.joinToString( separator = " | ")}"}
		movie.rating?.let { localView.controller_movie_ratingBar.rating = it.toFloat() }
	}
}