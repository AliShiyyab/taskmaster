package com.example.taskmaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import android.content.Context;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<Setting> settingRule =
            new ActivityScenarioRule<>(Setting.class);
    @Test
    public void testButtonsClicks() {
        onView(withId(R.id.editTextTextPersonName)).perform(typeText("Ali"));
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.homeUserName)).check(matches(withText("Ali tasks")));
    }
}