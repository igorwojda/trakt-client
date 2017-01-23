package com.igorwojda.traktclient.feature.trendingmovielist

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie

/**
 * Created by Panel on 22.01.2017
 */

interface TrendingMovieListContract{
	interface View : MvpLceView<List<TrendingMovie>>
}
