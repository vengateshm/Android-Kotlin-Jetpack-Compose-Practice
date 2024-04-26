package dev.vengateshm.compose_material3.deep_links

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import dev.vengateshm.compose_material3.foreground_service.counter_service.COUNTER_NOTIFICATION_CHANNEL_ID

class DeepLinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val notificationId = "navigation-tutorial".hashCode()
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("example://artemis.com/blog/navigation-tutorial")
            )
            val activity = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notificationManager = getSystemService<NotificationManager>()
            notificationManager?.notify(
                notificationId,
                NotificationCompat.Builder(this, COUNTER_NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("🧭 Navigation in Jetpack Compose")
                    .setContentText("Everything you need to know")
                    .setSmallIcon(android.R.drawable.ic_lock_silent_mode_off)
                    .setContentIntent(activity)
                    .build()
            )
        }
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "s1") {
                        composable(
                            route = "s1",
                        ) {
                            Screen1()
                        }
                        composable(
                            route = "s2?article={article}",
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "example://artemis.com/blog/{article}"
                                }
                            )
                        ) {
                            val article = it.arguments?.getString("article")
                            Screen2(article)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen1")
    }
}

@Composable
fun Screen2(article: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Showing / $article")
    }
}