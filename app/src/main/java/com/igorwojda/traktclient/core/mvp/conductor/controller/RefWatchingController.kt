package com.igorwojda.traktclient.core.mvp.conductor.controller

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.conductor.lce.MvpLceController
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.TraktClientApplication

abstract class RefWatchingController<CV : View, M, V: MvpLceView<M>, P : MvpPresenter<V>>(args: Bundle?) : MvpLceController<CV, M, V, P>(args){

	private var hasExited: Boolean = false

	public override fun onDestroy() {
		super.onDestroy()

		if (hasExited)
			TraktClientApplication.watch(this)
	}

	override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
		super.onChangeEnded(changeHandler, changeType)

		hasExited = !changeType.isEnter

		if (isDestroyed)
			TraktClientApplication.watch(this)
	}
}
