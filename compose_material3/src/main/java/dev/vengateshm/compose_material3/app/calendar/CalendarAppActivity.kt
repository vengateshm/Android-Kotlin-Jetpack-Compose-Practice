package dev.vengateshm.compose_material3.app.calendar

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.app.calendar.screens.AddEventScreen
import dev.vengateshm.compose_material3.app.calendar.screens.CalendarEventListScreen
import dev.vengateshm.compose_material3.app.calendar.screens.CalendarScreen

class CalendarAppActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWriteCalendarPermission()
        val viewModel = CalendarEventViewModel(CalendarEventRepoImpl(this))

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = CalendarScreen.CalendarEvents.route
            ) {
                composable(CalendarScreen.CalendarEvents.route) {
                    CalendarEventListScreen(viewModel) {
                        navController.navigate(CalendarScreen.AddEvent.route)
                    }
                }
                composable(CalendarScreen.AddEvent.route) {
                    AddEventScreen(viewModel) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

fun Context.requestWriteCalendarPermission() {
    val permissions = arrayOf(
        android.Manifest.permission.WRITE_CALENDAR,
        android.Manifest.permission.READ_CALENDAR
    )
    val isGranted =
        permissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    if (!isGranted) {
        // Request the permission.
        ActivityCompat.requestPermissions(
            this as Activity,
            permissions,
            REQUEST_CODE_WRITE_CALENDAR,
        )
    }
}

const val REQUEST_CODE_WRITE_CALENDAR = 101