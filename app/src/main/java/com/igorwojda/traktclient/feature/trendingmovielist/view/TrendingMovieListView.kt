package com.igorwojda.traktclient.feature.trendingmovielist.view

import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView

interface TrendingMovieListView : BaseLceView<List<TrendingMovie>>
