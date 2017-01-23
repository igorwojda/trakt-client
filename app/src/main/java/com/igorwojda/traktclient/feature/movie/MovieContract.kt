package com.igorwojda.traktclient.feature.movie

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.api.trakt.entities.Movie

/**
 * Created by Panel on 22.01.2017
 */

interface MovieContract{
	interface View : MvpLceView<Movie>
}