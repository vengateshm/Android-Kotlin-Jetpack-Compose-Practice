package dev.vengateshm.android_kotlin_compose_practice.nested_lazy_column

data class NestedLazyColumnData(
    val headerName: String,
    val list: List<Int>,
)

fun getNestedLazyColumnDataList() = mutableListOf<NestedLazyColumnData>()
    .apply {
        add(NestedLazyColumnData("One", mutableListOf(1, 2, 3, 4, 5)))
        add(NestedLazyColumnData("Two", mutableListOf(1, 2, 3, 4, 5)))
        add(NestedLazyColumnData("Three", mutableListOf(1, 2, 3, 4, 5)))
        add(NestedLazyColumnData("Four", mutableListOf(1, 2, 3, 4, 5)))
        add(NestedLazyColumnData("Five", mutableListOf(1, 2, 3, 4, 5)))
    }