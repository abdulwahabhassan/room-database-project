package com.decagon.roomdatabasetutorial.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Post (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var userId: Int,
    var title: String,
    var body: String) : Parcelable