package com.igorwojda.traktclient.feature.trendingmovielist

import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView
import com.igorwojda.traktclient.domain.entity.TrendingMovie

interface TrendingMovieListView : BaseLceView<List<TrendingMovie>>
