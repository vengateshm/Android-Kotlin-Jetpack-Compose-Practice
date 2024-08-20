package dev.vengateshm.compose_material3.ui_concepts.paging.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dev.vengateshm.compose_material3.ui_concepts.paging.data.local.BreweryDatabase
import dev.vengateshm.compose_material3.ui_concepts.paging.data.presentation.BreweriesViewModel
import dev.vengateshm.compose_material3.ui_concepts.paging.data.remote.BreweryApi
import dev.vengateshm.compose_material3.ui_concepts.paging.data.remote.BreweryRemoteMediator
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@OptIn(ExperimentalPagingApi::class)
val breweryAppModule = module {
    single<BreweryDatabase> {
        Room.databaseBuilder(
            androidContext(),
            BreweryDatabase::class.java,
            "breweries.db"
        ).build()
    }
    single<BreweryApi> {
        Retrofit.Builder()
            .baseUrl(BreweryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    single {
        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = BreweryRemoteMediator(
                localDataSource = get(),
                remoteDataSource = get()
            ),
            pagingSourceFactory = {
                get<BreweryDatabase>().breweryDao().pagingSource()
            }
        )
    }
    viewModel { BreweriesViewModel(get()) }
}