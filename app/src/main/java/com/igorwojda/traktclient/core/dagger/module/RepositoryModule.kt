package com.igorwojda.traktclient.core.dagger.module

import com.igorwojda.traktclient.feature.movie.model.MovieRepository
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

	@Singleton
	@Provides
	fun provideTrendingMovieListRepository() = TrendingMovieListRepository()

	@Singleton
	@Provides
	fun provideMovieRepository() = MovieRepository()
}