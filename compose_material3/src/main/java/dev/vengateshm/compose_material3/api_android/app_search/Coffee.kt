package dev.vengateshm.compose_material3.api_android.app_search

import androidx.appsearch.annotation.Document
import androidx.appsearch.annotation.Document.Id
import androidx.appsearch.annotation.Document.Namespace
import androidx.appsearch.annotation.Document.Score
import androidx.appsearch.annotation.Document.StringProperty
import androidx.appsearch.app.AppSearchSchema

@Document
data class Coffee(
    @Namespace
    val namespace: String,
    @Id
    val id: String,
    @Score
    val score: Int,

    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES,
    )
    val name: String,
)
