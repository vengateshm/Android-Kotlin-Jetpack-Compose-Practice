package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class UserCourses(val courses: List<String>)

suspend fun fetchCourses(userId: String): UserCourses {
  delay(100L)
  return UserCourses(listOf("Android", "Kotlin", "Java"))
}

fun coursesFlow(userId: String): Flow<UserCourses> = flow {
  val courses = fetchCourses(userId)
  emit(courses)
}

suspend fun fetchUser(userId: String): String {
  delay(100L)
  return "Vengateshm"
}

fun fetchUserFlow(userId: String): Flow<UserCourses> = flow {
  val userId = fetchUser(userId)
  coursesFlow(userId)
    .collect(::emit)
//  emitAll(coursesFlow(userId))
}