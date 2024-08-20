package dev.vengateshm.compose_material3.ui_concepts.paging.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.vengateshm.compose_material3.ui_concepts.paging.data.local.BreweryDatabase
import dev.vengateshm.compose_material3.ui_concepts.paging.data.local.BreweryEntity
import dev.vengateshm.compose_material3.ui_concepts.paging.data.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BreweryRemoteMediator(
    private val localDataSource: BreweryDatabase,
    private val remoteDataSource: BreweryApi
) : RemoteMediator<Int, BreweryEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BreweryEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1 // Initial page
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1 // Initial page
                    } else {
                        (state.pages.size / state.config.pageSize) + 1
                        //(lastItem.id.toInt() / state.config.pageSize) + 1
                    }
                }
            }
            val breweries = remoteDataSource.getBreweries(
                page = loadKey,
                pageCount = state.config.pageSize
            )
            localDataSource.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.breweryDao().clearAll()
                }
                localDataSource.breweryDao().upsertAll(breweries.map { it.toEntity() })
            }
            MediatorResult.Success(
                endOfPaginationReached = breweries.isEmpty(),
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}