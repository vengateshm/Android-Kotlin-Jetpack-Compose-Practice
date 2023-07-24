package dev.vengateshm.android_kotlin_compose_practice.nested_lazy_column

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowColumn

@Composable
fun NestedLazyColumn() {
    val list = getNestedLazyColumnDataList()
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(list) { item ->
                NestedLazyColumnListItem(item, onItemClick = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                })
            }
        })
}

@Composable
fun NestedLazyColumnListItem(
    nestedLazyColumnData: NestedLazyColumnData,
    onItemClick: (String) -> Unit,
) {
    Text(
        text = nestedLazyColumnData.headerName,
        style = TextStyle(color = Color.Black, fontSize = 20.sp),
        modifier = Modifier.padding(8.dp)
    )
    FlowColumn {
        nestedLazyColumnData.list.forEach { data ->
            Text(
                text = data.toString(),
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(data.toString()) })
        }
    }
}