package com.igorwojda.traktclient.core.dagger

import com.hannesdorfmann.mosby.conductor.sample.dagger.ControllerModule
import com.hannesdorfmann.mosby.conductor.sample.navigation.Navigator
import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import dagger.Component

@Component(modules = arrayOf(ControllerModule::class))

@ControllerScope
interface ControllerComponent {

  fun navigator(): Navigator

  fun trendingMoviePresenter(): TrendingMovieListPresenter
}