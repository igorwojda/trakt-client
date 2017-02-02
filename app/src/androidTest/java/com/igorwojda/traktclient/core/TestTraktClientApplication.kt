package com.igorwojda.traktclient.core

import com.igorwojda.traktclient.TraktClientApplication
import com.igorwojda.traktclient.core.dagger.module.RepositoryModule
import org.mockito.Mock

class TestTraktClientApplication : TraktClientApplication() {

	@Mock lateinit var repositoryModule: RepositoryModule

	override fun initDaggerComponents() {
//		val trending = Observable.f
//
//
//		`when`(repositoryModule.provideTrendingMovieListRepository().trending()).thenReturn()
//
//		applicationComponent = DaggerApplicationComponent.builder()
//				.presenterModule(PresenterModule(RepositoryModule()))
//				.build()
	}
}