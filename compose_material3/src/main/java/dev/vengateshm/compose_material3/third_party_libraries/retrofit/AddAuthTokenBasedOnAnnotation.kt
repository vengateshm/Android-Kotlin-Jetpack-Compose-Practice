package dev.vengateshm.compose_material3.third_party_libraries.retrofit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@Target(AnnotationTarget.FUNCTION)
annotation class AuthTokenNeeded

interface ApiService {
    @GET("api/breeds/list/all")
    @AuthTokenNeeded
    suspend fun getData(): retrofit2.Response<Any>
}

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())
        val shouldAddAuthToken = invocation.method().annotations
            .any { it.annotationClass == AuthTokenNeeded::class }
        return if (shouldAddAuthToken) {
            chain.proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader("Auth", "SECRET_TOKEN")
                    .build(),
            )
        } else {
            chain.proceed(chain.request())
        }
    }
}

val apiService: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl("https://dog.ceo/")
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(AuthenticationInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build(),
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

@Composable
fun DogApiSample(modifier: Modifier = Modifier) {
    var data by remember { mutableStateOf<Any?>(null) }
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        data = apiService.getData()
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        data?.let {
            Text(text = "Data: $it")
        } ?: run {
            Text(text = "Loading...")
        }
    }
}