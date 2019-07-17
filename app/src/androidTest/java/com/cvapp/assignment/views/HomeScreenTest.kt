package com.cvapp.assignment.views

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.cvapp.assignment.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Mathavan_K on 7/17/2019.
 */


@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule<HomeScreenActivity>(HomeScreenActivity::class.java)

    @Test
    fun initialViewsDisplayedProperly() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_save_profile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("Build Curriculum Vitae"))))
    }
}