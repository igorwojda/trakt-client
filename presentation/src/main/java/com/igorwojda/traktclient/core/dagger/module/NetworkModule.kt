package com.igorwojda.traktclient.core.dagger.module

import com.igorwojda.traktclient.core.net.merged.MergedMovieApi
import com.igorwojda.traktclient.core.net.trakt.TrakApi
import com.igorwojda.traktclient.core.net.wemakesites.WeMakeSitesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
	@Singleton @Provides
	fun provideTraktService() = TrakApi("0e7e55d561c7e688868a5ea7d2c82b17e59fde95fbc2437e809b1449850d4162",
			"acac2f8984eff126f39d2aa53ce08366733fda1013cc6e5e09699dadbbab517f")

	@Singleton @Provides
	fun provideWeMakeSitesService() = WeMakeSitesApi("a577382c-22b2-4a70-8ed4-efd50f945b8b")

	@Singleton @Provides
	fun provideMergedMovieService(trakApi: TrakApi, weMakeSitesApi: WeMakeSitesApi)
			= MergedMovieApi(trakApi, weMakeSitesApi)
}