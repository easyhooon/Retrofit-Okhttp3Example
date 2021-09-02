package com.kenshi.retrofitdemo

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>
    //This function will return a Retrofit Response Object of type Albums.

    //how do we decide the return type
    //-> Retrofit always give the result as a Retrofit Response object

    //after adding GET annotation, we need to include the URL end point
    //https://jsonplaceholder.typicode.com/albums
    //but this https://jsonplaceholder.typicode.com/ part
    //is the base of the API. which is common to all destination of the API

    //when we are creating the retrofit instance class.
    //For this function we will only add the url end point


    //Query parameter
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int) : Response<Albums>

    //Path parameter
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId:Int) : Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem) : Response<AlbumsItem>


}