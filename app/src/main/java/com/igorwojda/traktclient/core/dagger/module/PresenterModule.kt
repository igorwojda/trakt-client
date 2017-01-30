package com.igorwojda.traktclient.core.dagger.module

import com.igorwojda.traktclient.feature.movie.presenter.MoviePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule(var repositoryModule: RepositoryModule) {

	@Singleton
	@Provides
	fun provideTrendingMovieListPresenter() = TrendingMovieListPresenter(repositoryModule.provideTrendingMovieListRepository())

	@Singleton
	@Provides
	fun provideMoviePresenter() = MoviePresenter(repositoryModule.provideMovieRepository())
}