package com.igorwojda.traktclient

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * Created by Panel on 12.08.2016.
 */

class TraktClientApplication : Application() {

	companion object{
		private lateinit var refWatcher: RefWatcher

		fun watch(any:Any) = refWatcher.watch(any)
	}

    override fun onCreate() {
        super.onCreate()

		if (LeakCanary.isInAnalyzerProcess(this))
			return

		refWatcher = LeakCanary.install(this)
	}
}