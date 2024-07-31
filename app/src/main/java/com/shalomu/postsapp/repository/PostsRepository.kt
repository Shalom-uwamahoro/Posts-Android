package com.shalomu.postsapp.repository

import com.shalomu.postsapp.api.ApiClient
import com.shalomu.postsapp.api.PostsApiInterface
import com.shalomu.postsapp.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)

    suspend fun fetchPosts() : Response<List<Post>> {
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }
}