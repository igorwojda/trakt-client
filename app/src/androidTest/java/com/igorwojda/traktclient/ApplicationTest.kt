package com.igorwojda.traktclient

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTest {

	@Test
	fun testAppPackage() {
		val appContext = InstrumentationRegistry.getTargetContext()

		assertEquals("com.igorwojda.traktclient", appContext.packageName)
	}
}
