package dev.vengateshm.compose_material3.di.koin

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped
import org.koin.core.annotation.Single

@Module
@ComponentScan
class AnnotatedModule {
    @Single
    fun provideTestRepo(): TestRepo {
        return TestRepo()
    }

    @Factory
    fun provideTestUseCase(testRepo: TestRepo): TestUseCase {
        return TestUseCase(testRepo)
    }

    @Scope(KoinDiActivity::class)
    @Scoped
    fun provideConfigManager(): ConfigManager {
        return ConfigManager(config = "This is config")
    }

    @Scope(HumanScope::class)
    @Scoped
    fun provideHuman(): Human {
        return Human(name = "John")
    }

    @Factory
    @Named("Str1")
    fun provideString1(): String = "String1"

    @Factory
    @Named("Str2")
    fun provideString2(): String = "String2"
}