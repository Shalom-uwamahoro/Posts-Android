package com.shalomu.postsapp.ui

import com.shalomu.postsapp.api.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalomu.postsapp.model.Post
import com.shalomu.postsapp.api.PostsApiInterface
import com.shalomu.postsapp.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    val TAG : "COMMENTSACTIVITYTAG"
    lateinit var binding: ActivityCommentsBinding
    lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "COMMENTSACTIVITYTAG")
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            fetchPost(postId)
            fetchCommentsByPostId(postId)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        commentsAdapter = CommentsAdapter(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = commentsAdapter
    }

    fun fetchPost(postId: Int) {
        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
        val request = apiClient.fetchPostById(postId)

        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                } else {
                    Toast.makeText(this@CommentsActivity, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchCommentsByPostId(postId: Int) {
        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
        val request = apiClient.fetchCommentsByPostId(postId)

        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()
                    if (comments.isNotEmpty()) {
                        commentsAdapter.commentsList = comments
                        commentsAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@CommentsActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


}