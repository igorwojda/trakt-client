package com.igorwojda.traktclient.dagger

import com.igorwojda.traktclient.data.dagger.DataModule
import com.igorwojda.traktclient.domain.dagger.DomainModule
import com.igorwojda.traktclient.feature.movie.MoviePresenter
import com.igorwojda.traktclient.feature.trendingmovielist.TrendingMovieListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(presenter: TrendingMovieListPresenter)
    fun moviePresenter(): MoviePresenter

    fun inject(presenter: MoviePresenter)
    fun trendingMovieListPresenter(): TrendingMovieListPresenter
}