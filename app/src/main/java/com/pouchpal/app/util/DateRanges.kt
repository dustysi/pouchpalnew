package com.pouchpal.app.util

import java.time.ZoneId
import java.time.ZonedDateTime

fun startOfToday(zone: ZoneId = ZoneId.systemDefault()): ZonedDateTime {
    val now = ZonedDateTime.now(zone)
    return now.toLocalDate().atStartOfDay(zone)
}

fun startOfTomorrow(zone: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
    startOfToday(zone).plusDays(1)
