package dev.vengateshm.kotlin_practice.utils

import org.junit.jupiter.api.DisplayNameGenerator

class TestDisplayNameGenerator : DisplayNameGenerator {
  private val default = DisplayNameGenerator.Standard()

  override fun generateDisplayNameForClass(testClass: Class<*>): String {
    return default.generateDisplayNameForClass(testClass)
  }
}