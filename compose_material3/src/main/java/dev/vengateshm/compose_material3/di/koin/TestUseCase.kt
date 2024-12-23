package dev.vengateshm.compose_material3.di.koin

class TestUseCase(private val testRepo: TestRepo) {
    suspend fun getData(): String {
        return testRepo.getData()
    }
}