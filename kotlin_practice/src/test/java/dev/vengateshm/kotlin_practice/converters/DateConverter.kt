package dev.vengateshm.kotlin_practice.converters

import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConversionException
import org.junit.jupiter.params.converter.ArgumentConverter
import java.text.ParseException
import java.text.SimpleDateFormat

class DateConverter : ArgumentConverter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    override fun convert(source: Any?, context: ParameterContext?): Any {
        if (source !is String) {
            throw ArgumentConversionException("Invalid input: $source")
        }
        try {
            return dateFormat.parse(source)
        } catch (e: ParseException) {
            throw ArgumentConversionException("Failed to convert '$source' to a Date.", e)
        }
    }
}