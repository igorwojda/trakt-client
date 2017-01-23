package com.igorwojda.traktclient.core.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



/**
 * Created by Panel on 23.01.2017
 */
@Module
class AppModule(private var application: Application) {

	@Provides
	@Singleton
	fun provideApplication(): Application = application
}