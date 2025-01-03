package dev.vengateshm.android_kotlin_compose_practice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import dev.vengateshm.android_kotlin_compose_practice.manual_di.CallAppModule
import dev.vengateshm.android_kotlin_compose_practice.manual_di.CallAppModuleImpl
import dev.vengateshm.compose_material3.api_android.foreground_service.counter.CounterNotificationUtils
import dev.vengateshm.compose_material3.api_android.foreground_service.location_tracking.LocationTrackingService
import dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event.navigatorModule
import dev.vengateshm.compose_material3.di.koin.koinDiModules
import dev.vengateshm.compose_material3.di.koin.koinSampleModule
import dev.vengateshm.compose_material3.testing.mvvm_local_db.di.todoModule
import dev.vengateshm.compose_material3.third_party_libraries.koin_scope.koinScopeModule
import dev.vengateshm.compose_material3.ui_concepts.paging.di.breweryAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Character.LINE_SEPARATOR
import kotlin.system.exitProcess

@HiltAndroidApp
class AndroidKotlinComposePracticeApp : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        //StrictModeUtils.enableStrictMode(BuildConfig.DEBUG)

        // Manual DI
        callAppModule = CallAppModuleImpl(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    "location_channel_id",
                    "Location updates",
                    NotificationManager.IMPORTANCE_LOW,
                )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(CounterNotificationUtils.getCounterNotificationChannel())
            notificationManager.createNotificationChannel(LocationTrackingService.getLocationTrackingNotificationChannel())
        }

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            handleUncaughtException(throwable)
        }

        startKoin {
            androidContext(this@AndroidKotlinComposePracticeApp)
            androidLogger(level = Level.INFO)
            modules(
                todoModule,
                koinSampleModule,
                breweryAppModule,
                navigatorModule,
                koinScopeModule,
            )
            modules(koinDiModules)
        }
    }

    private fun handleUncaughtException(throwable: Throwable) {
        val intent = Intent(this.applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)

        println(getErrorString(throwable))

        android.os.Process.killProcess(android.os.Process.myPid())
        // Exit the current process
        // Non zero status - Abnormal termination
        exitProcess(10)
    }

    private fun getErrorString(throwable: Throwable): String {
        val stackTrace = StringWriter()
        throwable.printStackTrace(PrintWriter(stackTrace))
        val errorReport = StringBuilder()
        errorReport.append("************ CAUSE OF ERROR ************\n\n")
        errorReport.append(stackTrace.toString())

        errorReport.append("\n************ DEVICE INFORMATION ***********\n")
        errorReport.append("Brand: ")
        errorReport.append(Build.BRAND)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Device: ")
        errorReport.append(Build.DEVICE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Model: ")
        errorReport.append(Build.MODEL)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Id: ")
        errorReport.append(Build.ID)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Product: ")
        errorReport.append(Build.PRODUCT)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("\n************ FIRMWARE ************\n")
        errorReport.append("SDK: ")
        errorReport.append(Build.VERSION.RELEASE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Release: ")
        errorReport.append(Build.VERSION.RELEASE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Incremental: ")
        errorReport.append(Build.VERSION.INCREMENTAL)
        errorReport.append(LINE_SEPARATOR)

        return errorReport.toString()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            // Memory cache
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            // Disk cache
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            // Adding authorization token in header
            /*.callFactory {

            }*/
            .logger(DebugLogger())
            .build()
    }

    companion object {
        lateinit var callAppModule: CallAppModule
    }
}
