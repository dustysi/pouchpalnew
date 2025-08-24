package com.pouchpal.app.data

import java.time.Instant

interface TimeSource {
    fun now(): Instant

    object System : TimeSource {
        override fun now(): Instant = Instant.now()
    }
}
