package com.igorwojda.traktclient.feature.movie.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.controllers.base.BaseController
import com.igorwojda.traktclient.core.extensions.Bundle
import kotlinx.android.synthetic.main.controller_movie.view.*

//import com.igorwojda.traktclient.core.extensions.Bundle

/**
 * Created by Panel on 14.01.2017
 */
class MovieController(args: Bundle) : BaseController(args) {

	constructor(movieTraktId: String) : this(Bundle {
		putString(KEY_MOVIE_TRAKT_ID, movieTraktId)
	})

	companion object {
		private val KEY_MOVIE_TRAKT_ID = "MovieController.KEY_MOVIE_TRAKT_ID"
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_movie, container, false)

		var movieTraktId = args.getString(KEY_MOVIE_TRAKT_ID)

		view.controller_movie_title.text = movieTraktId
		title = movieTraktId

		view.setBackgroundColor(0xCCFFCC)
		return view
	}
}