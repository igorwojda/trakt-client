package com.igorwojda.traktclient.core.mvp.mosby

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import com.igorwojda.traktclient.core.mvp.mosbyconductor.BaseLceView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpBasePresenter<T>(){
	private val compositeDisposable = CompositeDisposable()

	val navigator by lazy { (view as? BaseLceView<*>)?.navigator }

    protected fun registerDisposable(subscription: Disposable) = compositeDisposable.add(subscription)

	override fun detachView(retainInstance: Boolean) {
		super.detachView(retainInstance)

		if (!retainInstance)
			compositeDisposable.clear()
	}
}