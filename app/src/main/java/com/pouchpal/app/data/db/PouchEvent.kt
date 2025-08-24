package com.pouchpal.app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "pouch_events")
data class PouchEvent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val instant: Instant
)
