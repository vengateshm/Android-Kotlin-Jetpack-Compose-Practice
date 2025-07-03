package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

data class Courses(val id: Int, val name: String, val topics: List<String>) {
  override fun toString(): String {
    return "Courses(id=$id, name='$name', topics=${topics.joinToString()})"
  }
}

class CoursesNetworkRepository {
  private var courseCounter = 0

  fun getCourses(): Flow<Courses> = flow {
    delay(1000)
    courseCounter++
    val newCourses = Courses(
      id = courseCounter,
      name = "Kotlin Programming Basics $courseCounter",
      topics = listOf("Variables", "Functions", "Coroutines", "Flow API"),
    )
    emit(newCourses)

    delay(5000)
    courseCounter++
    val updatedCourses = Courses(
      id = courseCounter,
      name = "Advanced Kotlin $courseCounter",
      topics = listOf("Generics", "Reflection", "DSL"),
    )
    emit(updatedCourses)
  }
}

class ViewModelWithCourses {

  private var lastCourses: Courses? = null
  private val coursesNetworkRepository = CoursesNetworkRepository()

  fun getCourses(): Flow<Courses> =
    coursesNetworkRepository.getCourses()
      .onEach {
        lastCourses = it
      }
      .onStart {
        lastCourses?.let { cachedCourses ->
          emit(cachedCourses)
        }
      }
}

suspend fun main() {
  val viewModel = ViewModelWithCourses()

  viewModel.getCourses().collect { courses ->
    println("Collector 1: Received Courses: $courses")
  }
  delay(1000)
  viewModel.getCourses().collect { courses ->
    println("Collector 2: Received Courses: $courses")
  }

  delay(6000)
  viewModel.getCourses().collect { courses ->
    println("Collector 3: Received Courses: $courses")
  }
}