package com.shalomu.postsapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shalomu.postsapp.databinding.PostsListItemBinding
import com.shalomu.postsapp.model.Post

class PostsAdapter (var postList: List<Post>, val context: Context): RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
       val post = postList[position]
        holder.binding.apply {
            tvPostBody.text = post.body
            tvPostTitle.text = post.title
            clPost.setOnClickListener{
                val intent = Intent(context, CommentsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}

class  PostsViewHolder(val binding: PostsListItemBinding):
        RecyclerView.ViewHolder(binding.root)