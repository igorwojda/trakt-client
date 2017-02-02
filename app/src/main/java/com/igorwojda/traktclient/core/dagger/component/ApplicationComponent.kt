package com.igorwojda.traktclient.core.dagger.component

import com.igorwojda.traktclient.core.dagger.module.NetworkModule
import com.igorwojda.traktclient.core.dagger.module.PresenterModule
import com.igorwojda.traktclient.core.dagger.module.RepositoryModule
import com.igorwojda.traktclient.feature.movie.presenter.MoviePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoryModule::class, PresenterModule::class, NetworkModule::class))
interface ApplicationComponent {

	fun inject(presenter: TrendingMovieListPresenter)
	fun moviePresenter(): MoviePresenter

	fun inject(presenter: MoviePresenter)
	fun trendingMovieListPresenter(): TrendingMovieListPresenter
}