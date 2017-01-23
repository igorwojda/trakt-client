package com.igorwojda.traktclient.core.mvp.mosby

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import rx.subscriptions.CompositeSubscription

/**
 * Created by Panel on 23.01.2017
 */

abstract class BasePresenter<T : MvpView> : MvpBasePresenter<T>(){
	val compositeSubscription = CompositeSubscription()

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance)
			compositeSubscription.clear()
	}
}