package dev.vengateshm.android_kotlin_compose_practice.nested_navigation.models

sealed class AppScreen(val name: String) {
    object Home : AppScreen("home")

    sealed class Onboarding(val screenName: String) : AppScreen(screenName) {
        object Login : Onboarding("login")

        object Register : Onboarding("register")

        object ForgotPassword : Onboarding("forgotpassword")

        companion object {
            val name = "onboarding"
        }
    }
}
