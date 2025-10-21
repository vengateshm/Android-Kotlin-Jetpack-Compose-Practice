package dev.vengateshm.compose_material3.api_android.work_manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakeLockWorkManagerScreen() {
  val context = LocalContext.current
  val workManager = WorkManager.getInstance(context)

  var workId by remember { mutableStateOf<UUID?>(null) }
  val workInfo = workId?.let { id ->
    workManager.getWorkInfoByIdLiveData(id).observeAsState()
  }

  Scaffold(
    topBar = { TopAppBar(title = { Text("WorkManager WakeLock Demo") }) },
  ) { padding ->
    Column(
      modifier = Modifier
        .padding(padding)
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Button(
        onClick = {
          val request = OneTimeWorkRequestBuilder<UploadWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
          workManager.enqueue(request)
          workId = request.id
        },
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text("Start Background Work")
      }

      workInfo?.value?.let { info ->
        Text("Work state: ${info.state}")

        if (info.state.isFinished) {
          Text("✅ Work completed successfully!")
        }
      }
    }
  }
}
