package com.igorwojda.traktclient.feature.trendingmovielist.view

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie

/**
 * Created by Panel on 22.01.2017
 */

interface TrendingMovieListView : MvpLceView<List<TrendingMovie>>
