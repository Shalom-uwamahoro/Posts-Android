package com.shalomu.postsapp.api

import com.shalomu.postsapp.ui.Comment
import com.shalomu.postsapp.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    fun fetchPostById(@Path("postId") postId: Int): Call<Post>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId: Int): Call<List<Comment>>


}
