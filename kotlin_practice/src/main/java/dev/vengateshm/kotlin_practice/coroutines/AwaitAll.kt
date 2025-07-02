package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

data class Article(
  val id: String,
  val title: String,
  val content: String,
  val publishedAt: Long,
)

interface ArticleRepository {
  suspend fun fetchArticle(id: String): Article
}

class DummyArticleRepository : ArticleRepository {
  private val articles = mutableMapOf<String, Article>()

  init {
    articles["article1"] = Article(
      "article1",
      "The Rise of Kotlin",
      "Kotlin is a modern programming language...",
      System.currentTimeMillis() - 100000,
    )
    articles["article2"] = Article(
      "article2",
      "Coroutines in Action",
      "Understanding asynchronous programming with coroutines...",
      System.currentTimeMillis() - 50000,
    )
    articles["article3"] = Article(
      "article3",
      "Android Development with Compose",
      "Building beautiful UIs with Jetpack Compose...",
      System.currentTimeMillis(),
    )
  }

  override suspend fun fetchArticle(id: String): Article {
    delay(100)
    return articles[id] ?: throw IllegalArgumentException("Article with ID $id not found")
  }
}

suspend fun <T, R> Iterable<T>.mapAsync(block: suspend (T) -> R): List<R> = coroutineScope {
  this@mapAsync.map { async { block(it) } }.awaitAll()
}

class FetchArticlesUseCase(
  private val articleRepository: ArticleRepository,
) {
  // suspend fun fetchArticles(ids: List<String>): List<Article> =
  //     kotlinx.coroutines.coroutineScope {
  //         ids.map { async { articleRepository.fetchArticle(it) } }
  //             .awaitAll()
  //             .sortedByDescending { it.publishedAt }
  //     }

  suspend fun fetchArticles(ids: List<String>): List<Article> =
    ids.mapAsync { articleRepository.fetchArticle(it) }
      .sortedByDescending { it.publishedAt }
}

suspend fun main() {
  val useCase = FetchArticlesUseCase(DummyArticleRepository())
  val articles = useCase.fetchArticles(listOf("article1", "article2", "article3"))
  println(articles)
}