package dev.vengateshm.kotlin_practice.type_erasure

import java.io.Reader
import java.io.StringReader
import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.transform.stream.StreamSource

fun main() {
    val s = secondItemOf(listOf("1", "2"))
    val i = secondItemOf(listOf(1, 2))

    val secondItemIsString = secondItemHasType<String>(listOf(1, "two", 3))
    println(secondItemIsString)
    val secondItemIsString1 = secondItemHasType1<String>(listOf(1, "two", 3))
    println(secondItemIsString1)

    val productXML = """
        <?xml version="1.0" encoding="UTF-8"?>
        <product>
            <id>100</id>
            <name>Laptop</name>
            <price>1500</price>
        </product>
    """.trimIndent()

    val unmarshaller = JAXBContext.newInstance(Product::class.java).createUnmarshaller()
    val product =
        unmarshaller.unmarshal(StreamSource(StringReader(productXML)), Product::class.java).value
    println(product)

    println(unmarshaller.unmarshall1<Product>(StringReader(productXML)))
}

fun <T> Unmarshaller.unmarshall(reader: Reader, clazz: Class<T>): T {
    return unmarshal(StreamSource(reader), clazz).value
}

inline fun <reified T> Unmarshaller.unmarshall1(reader: Reader): T {
    return unmarshal(StreamSource(reader), T::class.java).value
}

@XmlRootElement(name = "product")
data class Product @JvmOverloads constructor(
    @XmlElement
    var id: String = "",
    @XmlElement
    var name: String = "",
    @XmlElement
    var price: String = ""
)

fun <T> secondItemOf(items: List<T>): T {
    return items[1]
}

inline fun <reified T> secondItemHasType(items: List<*>): Boolean {
    return items[1] is T
}

inline fun <reified T> secondItemHasType1(items: List<Any>): Boolean {
    return items[1] is T
}

fun validSecondItemHasType() {
    val items: List<Any> = listOf(1, "two", 3)
    println(items[1] is String)
    val items1: List<*> = listOf(1, "two", 3)
    println(items1[1] is String)
}