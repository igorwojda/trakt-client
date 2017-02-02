package com.igorwojda.traktclient.feature.trendingmovielist.view

import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie
import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView

interface TrendingMovieListView : BaseLceView<List<TrendingMovie>>
