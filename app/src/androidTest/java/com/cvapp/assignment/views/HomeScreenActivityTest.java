package com.cvapp.assignment.views;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.cvapp.assignment.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class HomeScreenActivityTest {

    @Rule
    public ActivityTestRule<HomeScreenActivity> mActivityTestRule = new ActivityTestRule<>(HomeScreenActivity.class);

    @Test
    public void homeScreenActivityTest2() {

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_save_profile), withText("Create Profile"),
                        childAtPosition(
                                allOf(withId(R.id.lay_save_profile),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593734);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.txtfirstname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.txtfirstname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.txtfirstname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("mathavan"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.txtfirstname), withText("mathavan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.txtlastname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("Kaliyaperumal"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.txtdob),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textdobLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("3-105-1987"), closeSoftKeyboard());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.txtphone),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textphoneLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("8220451927"), closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.txtemailid),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textEmailLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("mathucs@hm.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.txtcityname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textcityLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("Chennai"), closeSoftKeyboard());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.txtcityname), withText("India"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textcityLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(pressImeActionButton());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.txtnationname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textnationLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("indian"), closeSoftKeyboard());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.txtnationname), withText("indian"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textnationLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_add), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3344631);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.txtcourse),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.txtcourse),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText14.perform(replaceText("BE"), closeSoftKeyboard());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.txtboard),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText15.perform(replaceText("Anna University"), closeSoftKeyboard());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.txtgrade),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textcityLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText16.perform(replaceText("B"), closeSoftKeyboard());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.txtyop),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLayYop),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText17.perform(replaceText("2009"), closeSoftKeyboard());

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.txtyop), withText("2009"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLayYop),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText18.perform(pressImeActionButton());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_save_edu), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3516317);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText19 = onView(
                allOf(withId(R.id.txtcoreskill),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText19.perform(click());

        ViewInteraction textInputEditText20 = onView(
                allOf(withId(R.id.txtcoreskill),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText20.perform(replaceText("Android,Core Java"), closeSoftKeyboard());

        ViewInteraction textInputEditText21 = onView(
                allOf(withId(R.id.txttotalexp),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textOtherSkilLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText21.perform(replaceText("9"), closeSoftKeyboard());

        ViewInteraction textInputEditText22 = onView(
                allOf(withId(R.id.txtProfSummary),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.txtLayProfSumm),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText22.perform(replaceText("Developer"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_addtech_skill), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3545326);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText25 = onView(
                allOf(withId(R.id.txtorganization),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText25.perform(click());

        ViewInteraction textInputEditText26 = onView(
                allOf(withId(R.id.txtorganization),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText26.perform(click());

        ViewInteraction textInputEditText27 = onView(
                allOf(withId(R.id.txtorganization),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText27.perform(replaceText("i"), closeSoftKeyboard());

        ViewInteraction textInputEditText28 = onView(
                allOf(withId(R.id.txtorganization), withText("i"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLastLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText28.perform(click());

        ViewInteraction textInputEditText29 = onView(
                allOf(withId(R.id.txtrole),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textRoleLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText29.perform(click());

        ViewInteraction textInputEditText30 = onView(
                allOf(withId(R.id.txtrole),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textRoleLayout1),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText30.perform(replaceText("gh"), closeSoftKeyboard());

        ViewInteraction textInputEditText31 = onView(
                allOf(withId(R.id.txtdurafrom),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textcityLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText31.perform(replaceText("y"), closeSoftKeyboard());

        ViewInteraction textInputEditText32 = onView(
                allOf(withId(R.id.txtdurto),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLayTo),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText32.perform(replaceText("uh"), closeSoftKeyboard());

        ViewInteraction textInputEditText33 = onView(
                allOf(withId(R.id.txtdurto), withText("uh"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLayTo),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText33.perform(pressImeActionButton());

        pressBack();

        ViewInteraction textInputEditText34 = onView(
                allOf(withId(R.id.txtresponsiblity),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText34.perform(click());

        pressBack();

        ViewInteraction textInputEditText37 = onView(
                allOf(withId(R.id.txtresponsiblity), withText("testing"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText37.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText38 = onView(
                allOf(withId(R.id.txtresponsiblity), withText("testing"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textLay),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText38.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_addtech_skill), withText("Save Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
