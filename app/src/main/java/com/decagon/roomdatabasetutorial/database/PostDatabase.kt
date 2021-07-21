package com.decagon.roomdatabasetutorial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decagon.roomdatabasetutorial.model.Post



@Database(entities = [Post::class], version = 1, exportSchema = false )
abstract class PostDatabase: RoomDatabase() {

    abstract fun postDao() : PostDao

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getDatabase(context: Context): PostDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "post_database"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}