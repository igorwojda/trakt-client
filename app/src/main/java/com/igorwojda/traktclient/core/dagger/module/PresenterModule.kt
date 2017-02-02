package com.igorwojda.traktclient.core.dagger.module

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import com.igorwojda.traktclient.feature.movie.presenter.MoviePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.presenter.TrendingMovieListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(RepositoryModule::class))
class PresenterModule {

	@Singleton @Provides
	fun provideTrendingMovieListPresenter(repositoryModule: RepositoryModule, mergedMovieService: MergedMovieApi)
			= TrendingMovieListPresenter(repositoryModule.provideTrendingMovieListRepository(mergedMovieService))

	@Singleton @Provides
	fun provideMoviePresenter(repositoryModule: RepositoryModule, mergedMovieService: MergedMovieApi)
			= MoviePresenter(repositoryModule.provideMovieRepository(mergedMovieService))
}

