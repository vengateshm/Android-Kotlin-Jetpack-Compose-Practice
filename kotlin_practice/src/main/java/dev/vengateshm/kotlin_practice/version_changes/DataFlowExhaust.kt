package dev.vengateshm.kotlin_practice.version_changes

enum class UserRole {
  ADMIN,
  MANAGER,
  USER
}

fun getSecurityClearance(role: UserRole): String {
  if (role == UserRole.ADMIN)
    return "Top Secret"
  return when (role) {
    UserRole.MANAGER -> "Confidential"
    UserRole.USER -> "Restricted"
  }
}

fun main() {
  println(getSecurityClearance(UserRole.ADMIN))
  println(getSecurityClearance(UserRole.MANAGER))
  println(getSecurityClearance(UserRole.USER))
}

