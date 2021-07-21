package com.decagon.roomdatabasetutorial.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.roomdatabasetutorial.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Query("DELETE FROM post")
    suspend fun deleteAllPosts()

    @Query("SELECT * FROM Post ORDER BY id DESC")
    fun readAllData(): LiveData<List<Post>>
}