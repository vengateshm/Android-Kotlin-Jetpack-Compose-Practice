package dev.vengateshm.compose_material3.ui_concepts.form_field_viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OnboardingScreen(
  viewModel: OnboardingViewModel = viewModel(),
) {

  val state by viewModel.uiState.collectAsStateWithLifecycle()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {

    OutlinedTextField(
      value = state.email,
      onValueChange = viewModel::onEmailChange,
      label = { Text("Email") },
      isError = state.emailError != null,
    )

    state.emailError?.let {
      Text(it, color = Color.Red)
    }

    OutlinedTextField(
      value = state.fullName,
      onValueChange = viewModel::onFullNameChange,
      label = { Text("Full Name") },
      isError = state.fullNameError != null,
    )

    state.fullNameError?.let {
      Text(it, color = Color.Red)
    }

    OutlinedTextField(
      value = state.birthDate,
      onValueChange = viewModel::onBirthDateChange,
      label = { Text("Birth Date") },
      placeholder = { Text("YYYY-MM-DD") },
      isError = state.birthDateError != null,
    )

    state.birthDateError?.let {
      Text(it, color = Color.Red)
    }

    OutlinedTextField(
      value = state.height,
      onValueChange = viewModel::onHeightChange,
      label = { Text("Height (cm)") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      isError = state.heightError != null,
    )

    state.heightError?.let {
      Text(it, color = Color.Red)
    }

    OutlinedTextField(
      value = state.weight,
      onValueChange = viewModel::onWeightChange,
      label = { Text("Weight (kg)") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      isError = state.weightError != null,
    )

    state.weightError?.let {
      Text(it, color = Color.Red)
    }

    Button(
      modifier = Modifier.fillMaxWidth(),
      onClick = { viewModel.onConfirmClick() },
    ) {
      Text("Confirm")
    }

    if (state.isSubmitted) {
      Text(
        text = "Form Submitted Successfully 🎉",
        color = Color.Green,
      )
    }
  }
}