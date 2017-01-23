package com.hannesdorfmann.mosby.conductor.sample.navigation

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.feature.movie.MovieController

interface Navigator {

	val router: Router

	fun showMovie(movie:Movie) {
		val handler = HorizontalChangeHandler()

		//ToDO: Pass movie object
		router.pushController(RouterTransaction.with(MovieController(movie) )
				.pushChangeHandler(handler)
				.popChangeHandler(handler)
		)
	}

}