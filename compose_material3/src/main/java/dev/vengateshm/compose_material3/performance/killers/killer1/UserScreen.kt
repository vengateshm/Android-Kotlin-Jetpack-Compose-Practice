package dev.vengateshm.compose_material3.performance.killers.killer1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen() {

  val repository: UserRepository = remember { FileUserRepository() }

  val viewModel: LoadUserViewModel = viewModel(
    factory = LoadUserViewModelFactory(repository),
  )

  val userData by viewModel.userData.collectAsStateWithLifecycle()
  val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    Button(
      onClick = {
        viewModel.loadUserData("user-123")
      },
    ) {
      Text("Load User Data")
    }

    Spacer(modifier = Modifier.height(24.dp))

    if (isLoading) {
      CircularProgressIndicator()
    } else {
      Text(userData)
    }
  }
}