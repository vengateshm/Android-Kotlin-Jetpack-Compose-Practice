package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProduceStateSample() {
    val productListState =
        produceState(
            initialValue =
                ProductListState(
                    isLoading = true,
                    listType = "vertical",
                    products = emptyList(),
                ),
        ) {
            Firebase.database.reference
                .child("dynamic_ui")
                .child("products")
                .get()
                .addOnSuccessListener { it1 ->
                    val productList =
                        it1.children.map { it2 ->
                            Product(
                                it2.child("id").getValue(Int::class.java) ?: -1,
                                it2.child("name").getValue(String::class.java) ?: "",
                                it2.child("description").getValue(String::class.java) ?: "",
                            )
                        }
                    Firebase.database.reference
                        .child("dynamic_ui")
                        .child("list_type")
                        .get()
                        .addOnSuccessListener {
                            value =
                                ProductListState(
                                    isLoading = false,
                                    listType = (if (it.value is String) it.value else "vertical") as String,
                                    products = productList,
                                )
                        }
                }
        }
    if (productListState.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
    if (productListState.value.products.isNotEmpty()) {
        when (productListState.value.listType) {
            "vertical" -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }

            "vertical_grid" -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }

            "staggered_vertical_grid" -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp,
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProduceStateSampleLive() {
    val productListState =
        produceState(
            initialValue =
                ProductListState(
                    isLoading = true,
                    listType = "vertical",
                    products = emptyList(),
                ),
        ) {
            val productsRef = Firebase.database.reference.child("dynamic_ui").child("products")
            val listTypeRef = Firebase.database.reference.child("dynamic_ui").child("list_type")
            lateinit var listTypeListener: ValueEventListener
            val productsListener =
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val productList =
                            dataSnapshot.children.map { it2 ->
                                Product(
                                    it2.child("id").getValue(Int::class.java) ?: -1,
                                    it2.child("name").getValue(String::class.java) ?: "",
                                    it2.child("description").getValue(String::class.java) ?: "",
                                )
                            }

                        listTypeListener =
                            object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    value =
                                        ProductListState(
                                            isLoading = false,
                                            listType = (if (snapshot.value is String) snapshot.value else "vertical") as String,
                                            products = productList,
                                        )
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    Log.w(
                                        "ERROR",
                                        "loadListType:onCancelled",
                                        databaseError.toException(),
                                    )
                                }
                            }
                        listTypeRef.addValueEventListener(listTypeListener)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.w("ERROR", "loadProducts:onCancelled", databaseError.toException())
                    }
                }
            productsRef.addValueEventListener(productsListener)

            awaitDispose {
                productsRef.removeEventListener(productsListener)
                listTypeRef.removeEventListener(listTypeListener)
            }
        }
    if (productListState.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
    if (productListState.value.products.isNotEmpty()) {
        when (productListState.value.listType) {
            "vertical" -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }

            "vertical_grid" -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }

            "staggered_vertical_grid" -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp,
                ) {
                    items(productListState.value.products) { product ->
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.LightGray,
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text(text = product.name)
                                Text(text = product.description)
                            }
                        }
                    }
                }
            }
        }
    }
}

data class ProductListState(
    val isLoading: Boolean = false,
    val listType: String,
    val products: List<Product>,
)

data class Product(
    val id: Int,
    val name: String,
    val description: String,
)
