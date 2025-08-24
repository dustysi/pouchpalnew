package com.pouchpal.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchTest {
    @Rule @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun launch() {
        // launches activity
    }
}
