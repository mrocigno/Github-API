package br.com.mrocigno.githubapi

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.mrocigno.githubapi.adapter.RepositoriesAdapter
import br.com.mrocigno.githubapi.utils.atPosition
import br.com.mrocigno.githubapi.view.HomeActivity
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class HomeEspressoTest {

    @get:Rule var rule : ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java, false, true)

    @Test
    fun testRecyclerResponse(){
        Thread.sleep(2000)
        onView(withId(R.id.rcyHome)).check(matches(isDisplayed()))
        // If showing "card_info", means api gave error
        onView(withId(R.id.rcyHome)).check(matches(not(atPosition(0, hasDescendant(withText(R.string.api_info))))))

        // If continue will scroll page to last item
        onView(withId(R.id.rcyHome)).perform(RecyclerViewActions.scrollToPosition<RepositoriesAdapter.ViewHolder>(9))
    }

}