package com.pouchpal.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PouchEventDao {
    @Insert
    suspend fun insert(event: PouchEvent)

    @Query("SELECT COUNT(*) FROM pouch_events WHERE instant >= :start AND instant < :end")
    fun countBetween(start: Long, end: Long): Flow<Int>
}
