package com.shalomu.postsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalomu.postsapp.model.Post
import com.shalomu.postsapp.api.ApiClient
import com.shalomu.postsapp.api.PostsApiInterface
import com.shalomu.postsapp.databinding.ActivityMainBinding
import com.shalomu.postsapp.viewmodel.PostsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val postsViewModel : PostsViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fetchPosts() no longer needed
        postsViewModel.fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart(){
        super.onStart()


    }


    override fun onResume(){
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { postsList ->
            displayPosts(postsList)
        })

        postsViewModel.erroLiveData.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }


    override fun onPause(){
        super.onPause()


    }

    override fun onStop() {
        super.onStop()


    }

    override fun onDestroy() {
        super.onDestroy()

    }


    fun displayPosts(posts: List<Post>){
        val postsAdapter = PostsAdapter(posts, this)
        binding.rvPosts.adapter = postsAdapter
    }


//    fun fetchPosts(){
//        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
//        val request = apiClient.fetchPosts()
//        request.enqueue(object : Callback<List<Post>>{
//            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
//                if (p1.isSuccessful){
//                    val posts = p1.body()
//                    if (posts != null) {
//                        displayPosts(posts)
//                    }
//                //    displayPosts(posts!!)  can work too as long as you are sure you won't need a null
//                    Toast.makeText(baseContext,"Fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    Toast.makeText(baseContext,p1.errorBody()?.string(),Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
//                Toast.makeText(baseContext,p1.message,Toast.LENGTH_LONG).show()
//            }
//        })

//    }   Because we no longer need fetchPosts() here


//apiClient is the object that implement our api interface.

}



