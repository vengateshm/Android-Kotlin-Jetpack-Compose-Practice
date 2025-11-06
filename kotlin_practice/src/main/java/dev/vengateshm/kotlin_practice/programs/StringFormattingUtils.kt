package dev.vengateshm.kotlin_practice.programs

// Display the last four digits
// replace other digits 1-for-1 with asterisks
// Preserve the country's formatting scheme
fun maskPhoneNumber(value: String): String {
  if (value.isEmpty() || value.length < 4) return value
  val lastFour = value.substring(value.length - 4).trim()
  val maskedAll = value.replace("\\d".toRegex(), "*")
    .substring(0, value.length - 4)
  return maskedAll + lastFour
}

fun maskPhone(phone: String): String {
  val digits = phone.filter { it.isDigit() }
  val lastFour = digits.takeLast(4)
  val maskedDigits = "*".repeat(digits.length - 4) + lastFour

  var index = 0
  return buildString {
    phone.forEach { ch ->
      append(if (ch.isDigit()) maskedDigits[index++] else ch)
    }
  }
}

// Display the first and last character of name
// Replace other characters 1-for-1 with asterisks
// Display @ symbol and domain
// Fully mask the name when 1 or 2 characters
fun maskEmail(value: String): String {
  if (value.isEmpty() || !value.contains("@")) return value
  var masked = ""
  val temp = value.substringBefore("@")
  val temp2 = "@" + value.substringAfter("@")
  if (temp.length < 3) {
    temp.forEach { _ -> masked += "*" }
    return masked + temp2
  }
  val lastChar =
    temp.substring(temp.length - 1)
  masked = temp.substring(0, 1)
  for (i in 1..temp.length - 2) {
    masked += "*"
  }
  return masked + lastChar + temp2
}

fun maskEmailAddress(email: String): String {
  val (name, domain) = email.split("@", limit = 2)
  val maskedName = when {
    name.length <= 2 -> "*".repeat(name.length)
    else -> "${name.first()}${"*".repeat(name.length - 2)}${name.last()}"
  }
  return "$maskedName@$domain"
}

fun maskString(string: String): String {
//  return string.substring(string.length - 3).padStart(string.length, '*')
  return string.takeLast(3).padStart(string.length, '*')
}

fun main() {
  val phones = listOf(
    "+1 (234) 567-8901",
    "+91-9876543210",
    "020 7946 0958",
  )
  phones.forEach { println(maskPhone(it)) }
  phones.forEach { println(maskPhoneNumber(it)) }

  val emails = listOf("a@domain.com", "ab@domain.com", "alex@domain.com", "samantha@domain.com")
  emails.forEach { println(maskEmail(it)) }
  emails.forEach { println(maskEmailAddress(it)) }

  println(maskString("1234567890"))
}
