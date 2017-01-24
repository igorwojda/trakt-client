package com.igorwojda.traktclient.core.mvp.mosbyconductor

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.igorwojda.traktclient.core.mvp.conductor.navigation.Navigator

/**
 * Created by Panel on 23.01.2017
 */
interface BaseView<M> : MvpLceView<M>{
	val navigator:Navigator
}
