package com.pouchpal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.pouchpal.app.data.Repository
import com.pouchpal.app.ui.theme.PouchPalTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val repository: Repository by lazy { (application as PouchPalApp).repository }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PouchPalTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(repository)
                }
            }
        }
    }
}

@Composable
fun MainScreen(repository: Repository) {
    val count by repository.todayCount.collectAsState(initial = 0)
    val scope = rememberCoroutineScope()
    Column {
        Text(text = count.toString(), style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { scope.launch { repository.logPouch() } }) {
            Text(text = stringResource(id = R.string.log_pouch))
        }
    }
}
