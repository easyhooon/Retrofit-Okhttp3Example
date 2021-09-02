package com.kenshi.retrofitdemo

//advanced gson annotation check

//the value of this annotation is the name to be used
//when serializing and deserializing these object
import com.google.gson.annotations.SerializedName

data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)
