package com.igorwojda.traktclient.feature.movie

import com.igorwojda.traktclient.core.mvp.mosby.BasePresenter
import com.igorwojda.traktclient.domain.entity.Movie
import com.igorwojda.traktclient.domain.interactor.GetMovieInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MoviePresenter @Inject constructor(private val getMovieInteractor: GetMovieInteractor)
    : BasePresenter<MovieView>() {

    fun loadMovie(movieTraktId: String) {
        val disposable = getMovieInteractor.execute(movieTraktId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            showMovie(it)
                        },
                        {
                            view?.showError(it, false)
                        })

        registerDisposable(disposable)
    }

    private fun showMovie(movie: Movie) {
        view?.setData(movie)
        view?.showContent()
    }

    override fun attachView(view: MovieView?) {
        super.attachView(view)
        view?.loadData(false)
    }
}
