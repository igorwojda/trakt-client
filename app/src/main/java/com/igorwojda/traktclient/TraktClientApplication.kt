package com.igorwojda.traktclient

import android.app.Application
import com.igorwojda.traktclient.core.api.trakt.TrakAPI
import com.igorwojda.traktclient.core.api.wemakesites.WeMakeSitesAPI
import com.igorwojda.traktclient.core.cache.DiskCache
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher


/**
 * Created by Panel on 14.08.2016
 */
class TraktClientApplication : Application() {

	companion object{
		lateinit var instance: TraktClientApplication

		private lateinit var refWatcher: RefWatcher

		fun watch(any:Any) = refWatcher.watch(any)

		val trakAPI by lazy { TrakAPI("0e7e55d561c7e688868a5ea7d2c82b17e59fde95fbc2437e809b1449850d4162",
				"acac2f8984eff126f39d2aa53ce08366733fda1013cc6e5e09699dadbbab517f") }
		val weMakeSitesAPI by lazy { WeMakeSitesAPI("a577382c-22b2-4a70-8ed4-efd50f945b8b") }

		val diskCache by lazy { DiskCache() }
	}

	override fun onCreate() {
		super.onCreate()

		instance = this

		if (LeakCanary.isInAnalyzerProcess(this))
			return

		refWatcher = LeakCanary.install(this)
	}
}