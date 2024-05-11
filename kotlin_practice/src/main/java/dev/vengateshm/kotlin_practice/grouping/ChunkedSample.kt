package dev.vengateshm.kotlin_practice.grouping

fun main() {
    // Book page
    // {0,1}, {2,3}, {4,5}, {6,7}, {8,9}
    // He can flip pages from either side
    // Find the minimum flips
    findMinimumFlips(6, 9)
}

fun findMinimumFlips(pageNo: Int, totalPages: Int) {
    val pages = (0..totalPages).chunked(2) // 2 because two pages if book opened
    println(pages)
    val pageIndex = pages.indexOfFirst { it.contains(pageNo) }
    println(pageIndex)
    val rightFlips = (pages.size - 1) - pageIndex
    println(rightFlips)
    val minFlips = pageIndex.coerceAtMost(rightFlips)
    println(minFlips)
}