package com.igorwojda.traktclient.core.mvp.conductor.controller

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.TraktClientApplication
import com.igorwojda.traktclient.core.mvp.conductor.navigation.PhoneNavigator
import kotlin.properties.Delegates

//public abstract class MvpLceController<CV extends TrendingMovieListView, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>

abstract class BaseController<CV : View, M, V:MvpLceView<M>, P : MvpPresenter<V>>(args: Bundle?) : RefWatchingController<CV, M, V, P>(args) {

	val navigator by lazy { PhoneNavigator(router) }
	val applicationComponent by lazy { TraktClientApplication.applicationComponent }

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
