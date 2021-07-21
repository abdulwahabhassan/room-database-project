package com.decagon.roomdatabasetutorial.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.decagon.roomdatabasetutorial.database.PostDatabase
import com.decagon.roomdatabasetutorial.database.PostRepository
import com.decagon.roomdatabasetutorial.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Post>>
    private val repository: PostRepository

    init {
        val postDao = PostDatabase.getDatabase(application).postDao()
        repository = PostRepository(postDao)
        readAllData = repository.readAllData
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePost(post)
        }
    }

    fun deleteAllPost() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPost()
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePost(post)
        }
    }
}