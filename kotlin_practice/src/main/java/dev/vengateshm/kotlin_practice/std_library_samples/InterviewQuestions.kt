package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    // Q1
    val impl = MyInterfaceImpl()
    impl.prop = 3
    impl.propertyWithImplementation = 4
    println(impl.prop)
    println(impl.propertyWithImplementation)
    print(impl.prop + impl.propertyWithImplementation)
    println()

    // Q2
    sr = "5"
    print(sr)

    // Q3
    println(3.squared)

    // Q4
    val h = HackerRankSample()
    println(h.ext("Hello"))

    // Q5
    //countTester()
}

//5
//1 3 5 6 8
//1
//2
//1
//6
//3
fun countTester() {
    val arrCount = readLine()!!.trim().toInt()

    val arr = Array<Int>(arrCount, { 0 })
    for (i in 0 until arrCount) {
        val arrItem = readLine()!!.trim().toInt()
        arr[i] = arrItem
    }

    val lowCount = readLine()!!.trim().toInt()

    val low = Array<Int>(lowCount, { 0 })
    for (i in 0 until lowCount) {
        val lowItem = readLine()!!.trim().toInt()
        low[i] = lowItem
    }

    val highCount = readLine()!!.trim().toInt()

    val high = Array<Int>(highCount, { 0 })
    for (i in 0 until highCount) {
        val highItem = readLine()!!.trim().toInt()
        high[i] = highItem
    }

    val result = countBetween(arr, low, high)

    println(result.joinToString("\n"))
}

fun countBetween(arr: Array<Int>, low: Array<Int>, high: Array<Int>): Array<Int> {
    // Write your code here
    val lowMin = low[0]
    val lowMax = low[low.size - 1]
    val highMin = high[0]
    val highMax = high[high.size - 1]

    // Check if low array empty
    // Check if high array empty

    return if (low.size == 1 && high.size == 1) {
        val outputArr = Array<Int>(1) { 0 }
        outputArr[0] = arr.filter { it in lowMin..highMin }.size
        outputArr
    } else {
        val outputArr = Array<Int>(2) { 0 }
        outputArr[0] = arr.filter { it in lowMin..lowMax }.size
        outputArr[1] = arr.filter { it in highMin..highMax }.size
        outputArr
    }
}

// Q1
interface MyInterface {
    var prop: Int
    val propertyWithImplementation: Int
        get() = 12
}

class MyInterfaceImpl : MyInterface {
    override var prop = 1
    override var propertyWithImplementation = 12
        get() = super.propertyWithImplementation + prop
        set(value) {
            field = value * 2
        }
}

// Q2
var sr = "4"
    set(value) {
        print("$value")
    }

// Q3
val Int.squared: Int
    get() = this * this

// Q4
class HackerRankSample {
    fun ext(str: String): Int {
        println("Member function called")
        return -1
    }
}

fun HackerRankSample.ext(str: String): Int {
    println("Extension function called")
    return -2
}