package dev.vengateshm.android_kotlin_compose_practice.pagination.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vengateshm.android_kotlin_compose_practice.pagination.pagingSource.UserDataSource
import dev.vengateshm.android_kotlin_compose_practice.pagination.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val userListPager = Pager(
        config = PagingConfig(pageSize = 10)
    ) {
        UserDataSource(userRepository)
    }.flow.cachedIn(viewModelScope)
}