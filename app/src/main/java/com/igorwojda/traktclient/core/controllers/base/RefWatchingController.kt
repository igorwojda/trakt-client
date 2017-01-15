package com.igorwojda.traktclient.core.controllers.base

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.igorwojda.traktclient.TraktClientApplication

abstract class RefWatchingController : Controller {

	protected constructor(args: Bundle?) : super(args)

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
