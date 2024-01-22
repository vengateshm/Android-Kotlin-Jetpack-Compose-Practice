package dev.vengateshm.android_kotlin_compose_practice.insta_reels_ui.mockData

import dev.vengateshm.android_kotlin_compose_practice.insta_reels_ui.models.Reel

object MockData {
    fun getReelList(): List<Reel> {
        return listOf(
            Reel(
                id = 1,
                video = "lake.mp4",
                userImage = "https://generated.photos/vue-static/home/hero/4.png",
                userName = "Vengatesh M",
                isLiked = true,
                likesCount = 778,
                commentsCount = 156,
                comment = "Nice...",
            ),
            Reel(
                id = 2,
                video = "food.mp4",
                userImage = "https://generated.photos/vue-static/home/hero/7.png",
                userName = "Alex Carey",
                isLiked = true,
                likesCount = 5923,
                commentsCount = 11,
                comment = "Super",
            ),
            Reel(
                id = 3,
                video = "icecream.mp4",
                userImage = "https://generated.photos/vue-static/home/hero/3.png",
                userName = "Mark Wood",
                isLiked = true,
                likesCount = 2314,
                comment = "Ah Oh...",
                commentsCount = 200,
            ),
            Reel(
                id = 4,
                video = "soap-bubbles.mp4",
                userImage = "https://generated.photos/vue-static/home/hero/6.png",
                userName = "Cak Jhon",
                isLiked = true,
                likesCount = 786,
                comment = "Yeahoo",
                commentsCount = 700,
            ),
            Reel(
                id = 5,
                video = "castle.mp4",
                userImage = "https://generated.photos/vue-static/home/hero/2.png",
                userName = "David Dulkader",
                isLiked = true,
                likesCount = 1890,
                comment = "Wow...",
                commentsCount = 232,
            ),
        )
    }
}
