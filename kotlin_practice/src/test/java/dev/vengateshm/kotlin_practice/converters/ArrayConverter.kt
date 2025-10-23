package dev.vengateshm.kotlin_practice.converters

import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConversionException
import org.junit.jupiter.params.converter.ArgumentConverter

class ArrayConverter : ArgumentConverter {
  override fun convert(
    source: Any?,
    context: ParameterContext,
  ): Any? {
    if (source !is String) {
      throw ArgumentConversionException("Invalid input: $source")
    }
    return source.split(",").map { it.toInt() }.toIntArray()
  }
}