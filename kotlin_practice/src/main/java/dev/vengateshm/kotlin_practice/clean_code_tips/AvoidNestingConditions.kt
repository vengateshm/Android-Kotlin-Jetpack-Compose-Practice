package dev.vengateshm.kotlin_practice.clean_code_tips

data class User(val age: Int, val hasSubscription: Boolean)

// Nested conditions : Hard to read
fun processUser1(user: User?) {
    if (user != null) {
        if (user.hasSubscription) {
            if (user.age >= 18) {
                showFullVersion()
            } else {
                showChildVersion()
            }
        } else {
            throw Exception("User needs a subscription")
        }
    } else {
        throw Exception("User not found")
    }
}

// Better one easy to understand
fun processUser(user: User?) {
    if (user == null)
        throw Exception("User not found")
    if (!user.hasSubscription)
        throw Exception("User needs a subscription")
    if (user.age < 18) {
        showChildVersion()
        return
    }
    showFullVersion()
}

fun showFullVersion() {
    TODO("Not yet implemented")
}

fun showChildVersion() {

}

fun main() {
    processUser(null)
}