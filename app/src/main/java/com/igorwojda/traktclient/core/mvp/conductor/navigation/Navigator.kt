package com.hannesdorfmann.mosby.conductor.sample.navigation

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.igorwojda.traktclient.feature.movie.view.MovieController

interface Navigator {

	val router: Router

	fun showMovie(movieTraktId:String) {
		val handler = HorizontalChangeHandler()

		//ToDO: Pass movie object
		router.pushController(RouterTransaction.with(MovieController(movieTraktId) )
				.pushChangeHandler(handler)
				.popChangeHandler(handler)
		)
	}

}