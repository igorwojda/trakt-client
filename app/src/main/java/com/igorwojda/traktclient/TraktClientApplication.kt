package com.igorwojda.traktclient

import android.app.Application
import com.facebook.stetho.Stetho
import com.igorwojda.traktclient.core.dagger.component.ApplicationComponent
import com.igorwojda.traktclient.core.dagger.component.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

open class TraktClientApplication : Application() {

	companion object {
		lateinit var instance: TraktClientApplication
        private lateinit var refWatcher: RefWatcher

		fun watch(any: Any) = refWatcher.watch(any)

		lateinit var applicationComponent: ApplicationComponent
	}

	override fun onCreate() {
		super.onCreate()

		instance = this

		if (!LeakCanary.isInAnalyzerProcess(this))
			refWatcher = LeakCanary.install(this)

		initDaggerComponents()
		initStetho()
	}

	private fun initStetho() {
		Stetho.initializeWithDefaults(this)
	}

	protected open fun initDaggerComponents() {
		applicationComponent = DaggerApplicationComponent.create()
	}
}