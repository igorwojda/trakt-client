package com.igorwojda.traktclient.feature.trendingmovielist.view

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseView

/**
 * Created by Panel on 22.01.2017
 */

interface TrendingMovieListView : BaseView<List<TrendingMovie>>
