package com.pouchpal.app

import com.pouchpal.app.util.startOfToday
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId

class DateRangesTest {
    @Test
    fun startOfTodayMidnight() {
        val zone = ZoneId.of("UTC")
        val start = startOfToday(zone)
        assertEquals(0, start.hour)
        assertEquals(0, start.minute)
    }
}
