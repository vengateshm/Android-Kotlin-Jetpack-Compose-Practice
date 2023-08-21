package dev.vengateshm.android_kotlin_compose_practice.room_db

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room

class RoomDbSampleActivity : ComponentActivity() {

    private val stockDb: StockDb by lazy {
        Room.databaseBuilder(
            this@RoomDbSampleActivity,
            StockDb::class.java,
            "stock-db"
        ).addCallback(StockDbCallback())
            .build()
    }

    private val viewModel: RoomDbSampleViewModel by viewModels(
        factoryProducer = {
            RoomDbSampleViewModelProvider(stockDao = stockDb.itemDao())
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.searchQuery,
                        onValueChange = viewModel::onSearchQueryChanged
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(viewModel.stocks) { stock ->
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = stock.name)
                                Text(text = stock.symbol)
                            }
                        }
                    }
                }
            }
        }
    }
}