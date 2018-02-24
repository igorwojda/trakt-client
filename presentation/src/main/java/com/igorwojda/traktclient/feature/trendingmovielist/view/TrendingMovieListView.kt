package com.igorwojda.traktclient.feature.trendingmovielist.view

import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView
import com.igorwojda.traktclient.core.net.trakt.entity.TrendingMovie

interface TrendingMovieListView : BaseLceView<List<TrendingMovie>>
