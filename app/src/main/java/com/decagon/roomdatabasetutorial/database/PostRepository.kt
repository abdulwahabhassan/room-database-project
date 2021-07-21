package com.decagon.roomdatabasetutorial.database

import androidx.lifecycle.LiveData
import com.decagon.roomdatabasetutorial.database.PostDao
import com.decagon.roomdatabasetutorial.model.Post

class PostRepository (private val postDao: PostDao) {

    val readAllData: LiveData<List<Post>> = postDao.readAllData()

    suspend fun addPost(post: Post) {
        postDao.addPost(post)
    }

    suspend fun deletePost(post: Post) {
        postDao.deletePost(post)
    }

    suspend fun deleteAllPost() {
        postDao.deleteAllPosts()
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePost(post)
    }
}