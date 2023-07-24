package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    //    println(Sample)
//    stringInitialization()
//    regexCaptureGroups()
}

// Available since kotlin 1.9.0
//data object Sample

fun stringInitialization() {
    val s1 = "Sample"
    val s2 = String("Sample".toCharArray())
    val s3 = "Sample"
    println("Equals ${s1 == s2}")
    println("Reference equals ${s1 === s2}")
    println("Reference equals ${s1 === s3}")
}

fun regexCaptureGroups() {
    // since 1.9.0
    // Coordinates: Austin, TX: 123
    val regex =
        "\\b(?<city>[A-Za-z\\s]+),\\s(?<state>[A-Z]{2}):\\s(?<areaCode>[0-9]{3})\\b".toRegex()
    val input = "Coordinates: Austin, TX: 123"
    val result = regex.find(input)!!
    println(result.groups["city"]?.value)
    println(result.groups["state"]?.value)
    println(result.groups["areaCode"]?.value)
    println(result.groups.size)
    println(result.groups[1]?.value)
    println(result.groups[2]?.value)
    println(result.groups[3]?.value)
}