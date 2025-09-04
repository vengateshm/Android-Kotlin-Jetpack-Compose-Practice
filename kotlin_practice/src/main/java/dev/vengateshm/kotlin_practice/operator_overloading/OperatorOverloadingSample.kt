package dev.vengateshm.kotlin_practice.operator_overloading

class Settings {
  private val map = mutableMapOf<String, Int>()

  fun putInt(key: String, value: Int) {
    map[key] = value
  }

  fun getInt(key: String): Int? = map[key]
}

operator fun Settings.set(
  key: String,
  value: Int,
): Unit = putInt(key, value)

operator fun Settings.get(
  key: String,
): Int? = getInt(key)

fun main() {
  val settings = Settings()

  settings["volume"] = 10
  println(settings["volume"])
}
