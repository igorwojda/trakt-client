package com.igorwojda.traktclient


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test

//@RunWith(AndroidJUnit4::class)
class TrendingMovieListTest {

	@JvmField @Rule
	var mActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

	@Test
	fun navigationToDetailAndBack() {


		onView(allOf(instanceOf(RecyclerView::class.java), withId(R.id.contentView), isDisplayed()))
				.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

		onView(withId(R.id.image)).check(matches(isDisplayed()))

//		onView(withId(R.id.recycleview))
//				.check(matches(hasDescendant(withText("Some text"))))

//		onView(nthChildOf(withId(R.id.recycleview), 0)
//				.check(matches(hasDescendant(withText("Some text"))))


		//xxxpresenter.dosth()
		//verify(xxxview).showAddNote

//		val recyclerView = onData(anything()).inAdapterView(withId(R.id.contentView))

//		recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))


//
//		pressBack()

	}
}
