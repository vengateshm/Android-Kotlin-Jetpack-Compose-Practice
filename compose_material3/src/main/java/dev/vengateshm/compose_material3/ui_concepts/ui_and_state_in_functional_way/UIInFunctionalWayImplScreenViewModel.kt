package dev.vengateshm.compose_material3.ui_concepts.ui_and_state_in_functional_way

import androidx.lifecycle.ViewModel

class UIInFunctionalWayImplScreenViewModel(
    private val repo: ProgrammingLanguageRepo = ProgrammingLanguageRepo()
) : ViewModel() {
    val data = repo.getProgrammingLanguages()
}