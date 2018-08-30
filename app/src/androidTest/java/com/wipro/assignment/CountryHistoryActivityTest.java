package com.wipro.assignment;


import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class CountryHistoryActivityTest {

    ActivityTestRule<CountryHistoryActivity> activityTestRule = new ActivityTestRule<>(CountryHistoryActivity.class);

    @Test
    public void checkAllFeedsLoaded() {
        activityTestRule.launchActivity(new Intent());
        RecyclerView recyclerView = (RecyclerView) activityTestRule.getActivity().findViewById(R.id.country_history_recycler_view);
        int itemsCount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(ViewMatchers.withId(R.id.country_history_recycler_view)).check(matches(isDisplayed()));
    }


    @Test
    public void checkErrorMessageIsDisplayedForNoInternet() {
        activityTestRule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.error_view)).check(matches(isDisplayed()));
    }

}
