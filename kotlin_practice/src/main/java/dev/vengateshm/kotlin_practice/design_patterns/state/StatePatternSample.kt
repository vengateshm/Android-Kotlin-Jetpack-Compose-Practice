package dev.vengateshm.kotlin_practice.design_patterns.state

enum class UserState {
    ANONYMOUS, UNVERIFIED, AUTHENTICATED, //LOCKED - compiler shows error if added new state
}

class AppUser(var email: String? = null, var state: UserState = UserState.ANONYMOUS) {
    fun signUp(email: String) {
        when (state) {
            UserState.ANONYMOUS -> {
                println("Signing up with email $email")
                state = UserState.UNVERIFIED
            }

            UserState.UNVERIFIED -> println("You are already signed up")
            UserState.AUTHENTICATED -> println("You are already signed up and authenticated")
        }
    }

    fun verifyEmail(token: String) {
        when (state) {
            UserState.ANONYMOUS -> println("You must sign up before verifying your email")
            UserState.UNVERIFIED -> {
                println("Verifying email with $token")
                state = UserState.AUTHENTICATED
            }

            UserState.AUTHENTICATED -> println("You are already verified")
        }
    }

    fun viewContent() {
        when (state) {
            UserState.ANONYMOUS -> println("Viewing public content.")
            UserState.UNVERIFIED -> println("Viewing personalized content for unverified account")
            UserState.AUTHENTICATED -> println("Viewing personalized content")
        }
    }

    fun viewProfile() {
        when (state) {
            UserState.ANONYMOUS -> println("You must sign in to view profile")
            UserState.UNVERIFIED -> println("Profile $email (Unverified account, please verify your email)")
            UserState.AUTHENTICATED -> println("Profile $email (Fully authenticated)")
        }
    }

    fun editProfile(newEmail: String) {
        when (state) {
            UserState.ANONYMOUS -> println("You must sign in to edit your profile")
            UserState.UNVERIFIED -> println("Please verify your email before editing your profile")
            UserState.AUTHENTICATED -> {
                println("Your profile update from $email to $newEmail")
                email = newEmail
            }
        }
    }
}

interface AppUserState {
    fun signUp(user: AppUser1, email: String)
    fun verifyEmail(user: AppUser1, token: String)
    fun viewContent()
    fun viewProfile(user: AppUser1)
    fun editProfile(user: AppUser1, newEmail: String)
}

object Anonymous : AppUserState {
    override fun signUp(user: AppUser1, email: String) {
        println("Signing up with email $email")
        user.email = email
        user.state = Unverified
    }

    override fun verifyEmail(user: AppUser1, token: String) =
        println("You must sign up before verifying your email")

    override fun viewContent() = println("Viewing public content.")

    override fun viewProfile(user: AppUser1) = println("You must sign in to view profile")

    override fun editProfile(user: AppUser1, newEmail: String) =
        println("You must sign in to edit your profile")
}

object Unverified : AppUserState {
    override fun signUp(user: AppUser1, email: String) = println("You are already signed up")

    override fun verifyEmail(user: AppUser1, token: String) {
        println("Verifying email with $token")
        user.state = Authenticated
    }

    override fun viewContent() = println("Viewing personalized content for unverified account")

    override fun viewProfile(user: AppUser1) =
        println("Profile ${user.email} (Unverified account, please verify your email)")

    override fun editProfile(user: AppUser1, newEmail: String) =
        println("Please verify your email before editing your profile")
}

object Authenticated : AppUserState {
    override fun signUp(user: AppUser1, email: String) =
        println("You are already signed up and authenticated")

    override fun verifyEmail(user: AppUser1, token: String) = println("You are already verified")

    override fun viewContent() = println("Viewing personalized content")

    override fun viewProfile(user: AppUser1) =
        println("Profile ${user.email} (Fully authenticated)")

    override fun editProfile(user: AppUser1, newEmail: String) {
        println("Your profile update from ${user.email} to $newEmail")
        user.email = newEmail
    }
}

class AppUser1(var email: String? = null, var state: AppUserState = Anonymous) {
    fun signUp(email: String) = state.signUp(this, email)
    fun verifyEmail(token: String) = state.verifyEmail(this, token)
    fun viewContent() = state.viewContent()
    fun viewProfile() = state.viewProfile(this)
    fun editProfile(newEmail: String) = state.editProfile(this, newEmail)
}

class AppUser2(var email: String? = null, var state: UserState1 = UserState1.ANONYMOUS) {
    fun signUp(email: String) = state.signUp(this, email)
    fun verifyEmail(token: String) = state.verifyEmail(this, token)
    fun viewContent() = state.viewContent()
    fun viewProfile() = state.viewProfile(this)
    fun editProfile(newEmail: String) = state.editProfile(this, newEmail)
}

enum class UserState1(
    val signUp: AppUser2.(String) -> Unit,
    val verifyEmail: AppUser2.(String) -> Unit,
    val viewContent: () -> Unit,
    val viewProfile: AppUser2.() -> Unit,
    val editProfile: AppUser2.(String) -> Unit,
) {
    ANONYMOUS(
        signUp = {
            println("Signing up with email $email")
            email = it
            state = UNVERIFIED
        },
        verifyEmail = { println("You must sign up before verifying your email") },
        viewContent = { println("Viewing public content.") },
        viewProfile = { println("You must sign in to view profile") },
        editProfile = { println("You must sign in to edit your profile") },
    ),
    UNVERIFIED(
        signUp = { println("You are already signed up") },
        verifyEmail = {
            println("Verifying email with $it")
            state = AUTHENTICATED
        },
        viewContent = { println("Viewing personalized content for unverified account") },
        viewProfile = { println("Profile $email (Unverified account, please verify your email)") },
        editProfile = { println("Please verify your email before editing your profile") },
    ),
    AUTHENTICATED(
        signUp = { println("You are already signed up and authenticated") },
        verifyEmail = { println("You are already verified") },
        viewContent = { println("Viewing personalized content") },
        viewProfile = { println("Profile $email (Fully authenticated)") },
        editProfile = {
            println("Your profile update from $email to $it")
            email = it
        },
    )
}

fun main() {
    fun main() {
        val user = AppUser2()
        user.viewContent()
        user.viewProfile()
        user.editProfile("newemail@example.com")
        user.signUp("user@example.com")
        user.viewContent()
        user.viewProfile()
        user.editProfile("newemail@example.com")
        user.verifyEmail("token123")
        user.viewContent()
        user.viewProfile()
        user.editProfile("newemail@example.com")
        user.viewProfile()
    }
    main()
}