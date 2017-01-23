package com.igorwojda.traktclient.core.mvp.conductor.controller

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.conductor.lce.MvpLceController
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import kotlin.properties.Delegates

//public abstract class MvpLceController<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>

abstract class BaseController<CV : View, M, V:MvpLceView<M>, P : MvpPresenter<V>> protected constructor(args: Bundle?) : MvpLceController<CV, M, V, P>(args) {

	protected val actionBar: ActionBar?
		get() = (router?.activity as AppCompatActivity?)?.supportActionBar

	var title:String by Delegates.observable("") {
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
			if (controller is BaseController<*, *, *, *>)
				return true

			controller = controller.parentController
		}

		return false
	}
}
