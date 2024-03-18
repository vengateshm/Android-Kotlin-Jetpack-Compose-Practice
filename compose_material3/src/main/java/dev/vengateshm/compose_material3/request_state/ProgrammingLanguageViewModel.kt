package dev.vengateshm.compose_material3.request_state

import androidx.lifecycle.ViewModel

class ProgrammingLanguageViewModel(
    private val repo: ProgrammingLanguageRepo = ProgrammingLanguageRepo()
) : ViewModel() {
    val data = repo.getProgrammingLanguages()
}