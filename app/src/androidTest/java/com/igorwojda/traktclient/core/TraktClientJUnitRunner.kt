package com.igorwojda.traktclient.core

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class TraktClientJUnitRunner : AndroidJUnitRunner() {
	override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
		return super.newApplication(cl, TestTraktClientApplication::class.java.name, context)
	}
}