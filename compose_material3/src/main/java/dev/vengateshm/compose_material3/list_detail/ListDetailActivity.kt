package dev.vengateshm.compose_material3.list_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.AnimatedPane
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class ListDetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {

                var selectedItem: MyItem? by rememberSaveable(stateSaver = MyItem.Saver) {
                    mutableStateOf(null)
                }

                val navigator = rememberListDetailPaneScaffoldNavigator()

                ListDetailPaneScaffold(
                    listPane = {
                        AnimatedPane(modifier = Modifier) {
                            MyItemList(onClick = {
                                selectedItem = it
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                            })
                        }
                    },
                    detailPane = {
                        AnimatedPane(modifier = Modifier) {
                            selectedItem?.run {
                                MyItemDetail(myItem = this)
                            }
                        }
                    },
                    extraPane = {
                        AnimatedPane(modifier = Modifier) {
                            Text(text = "Extra Pane")
                        }
                    })
            }
        }
    }
}

data class MyItem(val id: Int, val name: String) {
    companion object {
        val Saver: Saver<MyItem?, Map<String, Any>> = Saver(
            save = { item ->
                item?.run {
                    mapOf(
                        "id" to item.id,
                        "name" to item.name
                    )
                }
            },
            restore = { map ->
                MyItem(
                    id = map["id"] as Int,
                    name = map["name"] as String
                )
            }
        )

        fun getItemsList() = buildList {
            repeat(10) { index ->
                add(MyItem(id = index, name = "MyItem $index"))
            }
        }
    }
}

@Composable
fun MyItemList(onClick: (MyItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(MyItem.getItemsList()) { item ->
            Text(
                modifier = Modifier.clickable {
                    onClick(item)
                },
                text = item.name
            )
        }
    }
}

@Composable
fun MyItemDetail(myItem: MyItem) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Id : ${myItem.id}")
        Text(text = "Name : ${myItem.name}")
    }
}