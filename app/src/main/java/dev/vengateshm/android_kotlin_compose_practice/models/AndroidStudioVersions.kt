package dev.vengateshm.android_kotlin_compose_practice.models

data class AndroidStudioVersions(
    val name: String,
) {
    companion object {
        val versions =
            listOf(
                AndroidStudioVersions("Arctic Fox"),
                AndroidStudioVersions("Bumble Bee"),
                AndroidStudioVersions("Chipmunk"),
                AndroidStudioVersions("Dolphin"),
                AndroidStudioVersions("Electric Eel"),
                AndroidStudioVersions("Flamingo"),
                AndroidStudioVersions("Giraffe"),
            )
    }
}
