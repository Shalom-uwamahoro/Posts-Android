package com.shalomu.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shalomu.postsapp.model.Post
import com.shalomu.postsapp.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsRepo= PostsRepository()
    val erroLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Post>>()

    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.fetchPosts()
            if (response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                erroLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}