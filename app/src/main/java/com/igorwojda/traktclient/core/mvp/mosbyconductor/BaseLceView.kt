package com.igorwojda.traktclient.core.mvp.mosbyconductor

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.mvp.conductor.navigation.Navigator

interface BaseLceView<M> : MvpLceView<M>{
	val navigator:Navigator
}
