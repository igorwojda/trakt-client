package com.igorwojda.traktclient.core.mvp.mosby.extensions

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * Created by Panel on 23.01.2017
 */

fun <M> MvpLceView<M>.showError(message:String, pullToRefresh:Boolean) = showError(Exception(message), pullToRefresh)