package com.igorwojda.traktclient.core.extension

import android.os.Bundle

inline fun Bundle(body: Bundle.() -> Unit): Bundle {
	val bundle = Bundle()
	bundle.body()
	return bundle
}