package com.igorwojda.traktclient.core.mvp.mosby

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView
import rx.subscriptions.CompositeSubscription

abstract class BasePresenter<T : MvpView> : MvpBasePresenter<T>(){
	val compositeSubscription = CompositeSubscription()
	val navigator by lazy { (view as? BaseLceView<*>)?.navigator }

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance)
			compositeSubscription.clear()
	}
}