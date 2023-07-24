package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PerfTip2() {
    val nameList = remember {
        mutableStateListOf("Name 1", "Name 2")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            nameList.add(0, "Name 3")
        }) {
            Text(text = "Press")
        }
        NameList(names = nameList)
    }
}

@Composable
fun NameList(names: List<String>) {
    LazyColumn {
        items(names, key = { it.hashCode() }) {
            NameComposable(name = it)
        }
    }
}

@Composable
fun NameComposable(name: String) {
    println("Composition: $name")
    Text(text = name, fontSize = 25.sp)
}