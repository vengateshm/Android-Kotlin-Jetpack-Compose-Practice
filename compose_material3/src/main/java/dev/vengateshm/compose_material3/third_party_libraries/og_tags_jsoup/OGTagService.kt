package dev.vengateshm.compose_material3.third_party_libraries.og_tags_jsoup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class OGTagService {
    suspend fun getOgTags(url: String): OgTagData {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect(url).get()
            parseOgTags(document.html())
        }
    }

    private fun parseOgTags(html: String): OgTagData {
        val document = Jsoup.parse(html)
        val title = document.select("meta[property=og:title]").attr("content")
        val description = document.select("meta[property=og:description]").attr("content")
        val image = document.select("meta[property=og:image]").attr("content")
        val url = document.select("meta[property=og:url]").attr("content")

        return OgTagData(
            title = title?.ifEmpty { null },
            description = description?.ifEmpty { null },
            imageUrl = image?.ifEmpty { null },
            url = url?.ifEmpty { null },
        )
    }
}