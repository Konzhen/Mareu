package com.lamzone.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.maru.DI.DI;
import com.lamzone.maru.model.DummyMeetingApiService;
import com.lamzone.maru.utils.DeleteMeetingAction;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class MareuInstrumentedTests {

    private DummyMeetingApiService service;
    int itemCount;

    @Rule
    public ActivityTestRule<MeetingActivity> mMeetingActivityTestRule = new ActivityTestRule<MeetingActivity>(MeetingActivity.class) {

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            service = (DummyMeetingApiService) DI.getMeetingApiService();
            service.createTestList();
            itemCount = service.getMeetings().size();
        }

    };

        @Test
        public void myMeetingList_shouldNotBeEmpty() {
            onView(withId(R.id.meetingRecycler))
                    .check(matches(hasMinimumChildCount(1)));
        }

        @Test
        public void myMeetingList_deleteAction_shouldRemoveItem() {
            onView(withId(R.id.meetingRecycler))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(4, new DeleteMeetingAction()))
                    .check(withItemCount(itemCount - 1));
        }

        @Test
        public void writeWrongEmail_withSuccess() {
            onView(withId(R.id.createMeetingButton))
                    .perform(click());
            onView(withId(R.id.email))
                    .perform(click())
                    .perform(typeText("MonsieurTest@wrongTest"));
            onView(withId(R.id.addEmail))
                    .perform(click());
            onView(withId(R.id.chipGroup))
                    .check(matches(not(hasMinimumChildCount(1))));
        }

        @Test
        public void writeValidEmail_withSuccess() {
            onView(withId(R.id.createMeetingButton))
                    .perform(click());
            onView(withId(R.id.email))
                    .perform(click())
                    .perform(typeText("MonsieurTest@valideTest.com"));
            onView(withId(R.id.addEmail))
                    .perform(click());
            onView(withId(R.id.chipGroup))
                    .check(matches(hasMinimumChildCount(1)));
        }

        @Test
        public void createMeeting_withSuccess() {
            onView(withId(R.id.createMeetingButton))
                    .perform(click());
            onView(withId(R.id.email))
                    .perform(click())
                    .perform(typeText("MonsieurPlusUn@Cestok.com"));
            onView(withId(R.id.addEmail))
                    .perform(click());
            onView(withId(R.id.sujet))
                    .perform(click())
                    .perform(typeText("C'est l'heure du test !"));
            onView(withId(R.id.timePicker))
                    .perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(18, 24));
            onView(withText("OK"))
                    .perform(click());
            onView(withId(R.id.datePicker))
                    .perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(2021, 5, 2));
            onView(withText("OK"))
                    .perform(click());
            onView(withId(R.id.placeSpinner))
                    .perform(click());
            onData(allOf(is(instanceOf(String.class)),
                    is("Salle B"))).inRoot(isPlatformPopup()).perform(click());
            onView(withText(R.string.positiveButton))
                    .perform(click());
            onView(withId(R.id.meetingRecycler))
                    .check(withItemCount(itemCount + 1));
        }

        @Test
        public void applyDateFilter_withSuccess() {
            onView(withId(R.id.sort_button))
                    .perform(click());
            onView(withId(R.id.dialogDate))
                    .perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(2021, 4, 29));
            onView(withText("OK"))
                    .perform(click());
            onView(withText(R.string.positiveButton))
                    .perform(click());
            onView(withId(R.id.meetingRecycler))
                    .check(withItemCount(3));
        }

        @Test
        public void applyRoomFilter_withSuccess() {
            onView(withId(R.id.sort_button))
                    .perform(click());
            onView(withId(R.id.dialogSpinner))
                    .perform(click());
            onData(allOf(is(instanceOf(String.class)),
                    is("Salle G"))).inRoot(isPlatformPopup()).perform(click());
            onView(withText(R.string.positiveButton))
                    .perform(click());
            onView(withId(R.id.meetingRecycler))
                    .check(withItemCount(3));
        }

        @Test
        public void applyBothFilter_withSuccess() {
            onView(withId(R.id.sort_button))
                    .perform(click());
            onView(withId(R.id.dialogDate))
                    .perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(2021, 4, 30));
            onView(withText("OK"))
                    .perform(click());
            onView(withId(R.id.dialogSpinner))
                    .perform(click());
            onData(allOf(is(instanceOf(String.class)),
                    is("Salle G"))).inRoot(isPlatformPopup()).perform(click());
            onView(withText(R.string.positiveButton))
                    .perform(click());
            onView(withId(R.id.meetingRecycler))
                    .check(withItemCount(2));
        }

}

