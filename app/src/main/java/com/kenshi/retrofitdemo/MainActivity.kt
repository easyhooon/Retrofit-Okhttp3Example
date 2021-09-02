package com.kenshi.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.kenshi.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService : AlbumService
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        retService = RetrofitInstance.
            getRetrofitInstance().
            create(AlbumService::class.java)

        getRequest()

//        getRequestWithQueryParameters()

//        uploadAlbum()


    }

    private fun getRequest() {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            //val response = retService.getSortedAlbums(3)
            emit(response)

        }
        responseLiveData.observe(this, Observer {
            //this "it" here, represents a Retrofit Response Object of type Albums
            val albumsList = it.body()?.listIterator()

            if(albumsList != null) {
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    val result = " "+"Album Title : ${albumsItem.title}"+"\n"+
                            " "+"Album id : ${albumsItem.id}"+"\n"+
                            " "+"User id : ${albumsItem.userId}"+"\n\n\n"

                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithQueryParameters(){
        //path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
//            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()
            binding.textView.text = title
        })
    }

    private fun uploadAlbum() {
        val album = AlbumsItem(0, "My title", 3)

        val postResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, Observer{
            val receivedAlbumsItem = it.body()

            val result = " "+"Album Title : ${receivedAlbumsItem?.title}"+"\n"+
                    " "+"Album id : ${receivedAlbumsItem?.id}"+"\n"+
                    " "+"User id : ${receivedAlbumsItem?.userId}"+"\n\n\n"
            binding.textView.text = result
        })
    }
}


