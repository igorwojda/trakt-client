package com.igorwojda.traktclient.core.dagger

import com.igorwojda.traktclient.core.mvp.conductor.navigation.Navigator
import dagger.Component

@Component(modules = arrayOf(ControllerModule::class))

@ControllerScope
interface ControllerComponent {

  fun navigator(): Navigator

//  fun trendingMoviePresenter(): TrendingMovieListPresenter
}