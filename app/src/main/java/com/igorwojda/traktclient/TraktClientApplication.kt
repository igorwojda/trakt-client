package com.igorwojda.traktclient

import android.app.Application
import com.igorwojda.traktclient.core.api.trakt.TrakAPI
import com.igorwojda.traktclient.core.api.wemakesites.WeMakeSitesAPI
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
* Created by Panel on 14.08.2016
*/
class TraktClientApplication : Application() {

	companion object{
		private lateinit var refWatcher: RefWatcher
		fun watch(any:Any) = refWatcher.watch(any)

		val trakAPI by lazy { TrakAPI("", "") }
		val weMakeSitesAPI by lazy { WeMakeSitesAPI("") }
	}

    override fun onCreate() {
        super.onCreate()

		if (LeakCanary.isInAnalyzerProcess(this))
			return

		refWatcher = LeakCanary.install(this)
	}
}