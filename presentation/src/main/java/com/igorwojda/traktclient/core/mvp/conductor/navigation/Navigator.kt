package com.igorwojda.traktclient.core.mvp.conductor.navigation

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import com.igorwojda.traktclient.feature.movie.view.MovieController

interface Navigator {
	val router: Router

	fun showMovie(movie:Movie) {
		val id = movie.ids?.trakt ?: return
		val handler = HorizontalChangeHandler()

		val controller = RouterTransaction.with(MovieController(id))
				.pushChangeHandler(handler)
				.popChangeHandler(handler)

		router.pushController(controller)
	}
}