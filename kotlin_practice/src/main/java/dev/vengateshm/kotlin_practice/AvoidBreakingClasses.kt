package dev.vengateshm.kotlin_practice

class ListBucketsRequest(builder: Builder) {

  val bucketRegion = builder.bucketRegion
  val contToken = builder.contToken
  val maxBuckets = builder.maxBuckets

  companion object {
    operator fun invoke(block: Builder.() -> Unit) = Builder().apply(block).build()
  }

  class Builder {
    var bucketRegion: String? = null
    var contToken: String? = null
    var maxBuckets: Int? = null
    fun build() = ListBucketsRequest(this)
  }
}

fun main() {
  val request = ListBucketsRequest {
    bucketRegion = "us-west-2"
    contToken = null
    maxBuckets = 1000
  }
  println(request.bucketRegion)
}