package dev.vengateshm.kotlin_practice.dsl_example

fun client(block: ClientBuilder.() -> Unit): Client {
    val clientBuilder = ClientBuilder()
    clientBuilder.block()
    //block(clientBuilder)
    return clientBuilder.build()
}

fun ClientBuilder.twitter(block: TwitterBuilder.() -> Unit) {
    twitter = TwitterBuilder().apply(block).build()
}

fun ClientBuilder.company(block: CompanyBuilder.() -> Unit) {
    company = CompanyBuilder().apply(block).build()
}