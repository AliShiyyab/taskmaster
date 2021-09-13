package com.example.taskmaster;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class Tests {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testButtonsClicks() {
        onView((withId(R.id.SignOut))).perform(click());
    }
    @Test
    public void testAddTaskButton(){
        onView((withId(R.id.button3))).perform(click());
    }
    @Test
    public void testRecyclerView(){
        onView(withId(R.id.taskRecucleView)).perform(click());
    }
    @Test
    public void testHeader(){
        onView(withId(R.id.textView)).check(matches(withText("My Tasks")));
    }
}
