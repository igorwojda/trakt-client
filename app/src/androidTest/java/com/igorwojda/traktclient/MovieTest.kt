package com.igorwojda.traktclient

import android.support.test.runner.AndroidJUnit4
import com.igorwojda.traktclient.core.net.trakt.entity.Movie
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness

@RunWith(AndroidJUnit4::class)
class MovieTest {

	@Rule @JvmField var rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

	@Mock lateinit var movie: Movie

	@Test
	fun testMovie() {
		given(movie.title).willReturn("Yes!")

		assertEquals(movie.title, "Yes!")
	}
}
