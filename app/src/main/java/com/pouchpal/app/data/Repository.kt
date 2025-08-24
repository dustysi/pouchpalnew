package com.pouchpal.app.data

import com.pouchpal.app.data.db.PouchEvent
import com.pouchpal.app.data.db.PouchEventDao
import com.pouchpal.app.util.startOfToday
import com.pouchpal.app.util.startOfTomorrow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.ZoneId

class Repository(
    private val dao: PouchEventDao,
    private val timeSource: TimeSource,
    scope: CoroutineScope
) {
    private val refresh = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }

    init {
        // naive midnight refresh using delay
        scope.launch {
            while (true) {
                val zone = ZoneId.systemDefault()
                val start = startOfTomorrow(zone)
                val delayMs = start.toInstant().toEpochMilli() - timeSource.now().toEpochMilli()
                kotlinx.coroutines.delay(delayMs)
                refresh.emit(Unit)
            }
        }
    }

    val todayCount: Flow<Int> = refresh.flatMapLatest {
        val zone = ZoneId.systemDefault()
        val start = startOfToday(zone).toInstant().toEpochMilli()
        val end = startOfTomorrow(zone).toInstant().toEpochMilli()
        dao.countBetween(start, end)
    }

    suspend fun logPouch() {
        dao.insert(PouchEvent(instant = timeSource.now()))
        refresh.emit(Unit)
    }
}
