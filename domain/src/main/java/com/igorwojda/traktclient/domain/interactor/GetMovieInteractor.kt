package com.igorwojda.traktclient.domain.interactor

import com.igorwojda.traktclient.domain.repository.TraktRepository
import javax.inject.Inject

class GetMovieInteractor @Inject constructor(private val traktRepository: TraktRepository) {
    fun execute(traktMovieId: String) = traktRepository.getMovie(traktMovieId)
}