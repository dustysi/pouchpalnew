package com.pouchpal.app

import android.app.Application
import androidx.room.Room
import com.pouchpal.app.data.Repository
import com.pouchpal.app.data.TimeSource
import com.pouchpal.app.data.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PouchPalApp : Application() {
    private val scope = CoroutineScope(SupervisorJob())
    private val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "pouchpal.db").build()
    }
    val repository by lazy { Repository(database.pouchEventDao(), TimeSource.System, scope) }
}
