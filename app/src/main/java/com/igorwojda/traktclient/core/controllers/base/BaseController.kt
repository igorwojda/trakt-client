package com.igorwojda.traktclient.core.controllers.base

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlin.properties.Delegates

abstract class BaseController protected constructor(args: Bundle?) : RxController(args) {

	protected val actionBar: ActionBar?
		get() = (router?.activity as AppCompatActivity?)?.supportActionBar

	var title:String by Delegates.observable("title") {
		prop, old, new -> updateTitle()
	}

	override fun onAttach(view: View) {
		updateTitle()
		super.onAttach(view)
	}

	private fun updateTitle() {
		if(actionBar == null || hasTitleInParentHierarchy())
			return

		actionBar?.title = title
	}

	private fun hasTitleInParentHierarchy():Boolean{
		var controller = parentController

		while (controller != null) {
			if (controller is BaseController)
				return true

			controller = controller.parentController
		}

		return false
	}
}
