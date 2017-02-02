package com.igorwojda.traktclient.core.dagger.module

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import com.igorwojda.traktclient.feature.movie.model.MovieRepository
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
open class RepositoryModule @Inject constructor() {

	@Singleton @Provides
	fun provideTrendingMovieListRepository(mergedMovieService: MergedMovieApi)
			= TrendingMovieListRepository(mergedMovieService)

	@Singleton @Provides
	fun provideMovieRepository(mergedMovieService: MergedMovieApi)
			= MovieRepository(mergedMovieService)
}