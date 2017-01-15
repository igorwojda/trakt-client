package com.igorwojda.traktclient.core.extensions

import android.os.Bundle

/**
 * Created by Panel on 15.01.2017
 */

inline fun Bundle(body: Bundle.() -> Unit): Bundle {
	val bundle = Bundle()
	bundle.body()
	return bundle
}