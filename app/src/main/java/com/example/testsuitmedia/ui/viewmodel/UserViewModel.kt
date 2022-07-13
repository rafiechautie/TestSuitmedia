package com.example.testsuitmedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testsuitmedia.data.paging.UserRepository
import com.example.testsuitmedia.data.response.DataItem

class UserViewModel: ViewModel()  {

    private val repository = UserRepository()
    val users: LiveData<PagingData<DataItem>> =
        repository.getUsers().cachedIn(viewModelScope)

}