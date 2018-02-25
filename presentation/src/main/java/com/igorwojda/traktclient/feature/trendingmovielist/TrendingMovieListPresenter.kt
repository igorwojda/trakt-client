package com.igorwojda.traktclient.feature.trendingmovielist

import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.domain.entity.Movie
import com.igorwojda.traktclient.domain.entity.TrendingMovie
import com.igorwojda.traktclient.domain.interactor.GetTrendingMovieListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TrendingMovieListPresenter @Inject constructor(private val getTrendingMovieListInteractor: GetTrendingMovieListInteractor)
    : BasePresenter<TrendingMovieListView>() {

    private var trendingMovies: List<TrendingMovie>? = null

    fun loadTrendingMovies() =
            trendingMovies?.let { showTrendingMovies(it) } ?: loadTrendingMoviesFromRepository()

    private fun loadTrendingMoviesFromRepository() {
        val disposable = getTrendingMovieListInteractor.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            showTrendingMovies(it)
                            trendingMovies = it
                        },
                        {
                            view?.showError(it, false)
                        }
                )

        registerDisposable(disposable)
    }

    private fun showTrendingMovies(list: List<TrendingMovie>) {
        view?.setData(list)
        view?.showContent()
    }

    override fun attachView(view: TrendingMovieListView?) {
        super.attachView(view)
        view?.loadData(false)
    }

    fun navigateToMovie(movie: Movie) = movie.let { navigator?.showMovie(it) }
}