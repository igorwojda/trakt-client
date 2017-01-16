package com.igorwojda.traktclient.core.controller.base

import android.os.Bundle
import rx.subscriptions.CompositeSubscription

/**
 * Created by Panel on 15.01.2017
 */

abstract class RxController protected constructor(args: Bundle?) : RefWatchingController(args) {

	protected val compositeSubscription = CompositeSubscription()

	override fun onDestroy() {
		super.onDestroy()

		compositeSubscription.clear()
	}
}
