package dev.vengateshm.kotlin_practice.std_library_samples

import dev.vengateshm.kotlin_practice.std_library_samples.ApiOperation.Companion.get

@JvmInline
value class ApiOperation<I, O>(val path: String) {
    companion object {
        fun <O> get(path: String) = ApiOperation<Unit, O>(path)
        fun <I, O> post(path: String) = ApiOperation<I, O>(path)
    }
}

class Profile

class ApiClient {
    fun get(profile: ApiOperation<Unit, Profile>): Result<Profile> {
        profile.path
        return Result.success(Profile())
    }
}

fun main() {
    val apiClient = ApiClient()
    val profile = get<Profile>("me")
    println(apiClient.get(profile))
}