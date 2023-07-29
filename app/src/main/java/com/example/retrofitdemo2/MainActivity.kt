package com.example.retrofitdemo2

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitdemo2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val BASE_URL= "https://www.omdbapi.com/"
    private val API_KEY ="d16b94f8"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMovie("Spiderman")

    }
    private fun getMovie(title:String){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDataInterface::class.java)

        api.getMovieDetails(API_KEY,title).enqueue(object:Callback<MovieData>{
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if(response.isSuccessful){
                    var movieData = response.body()
                    Log.d("MYAPI",movieData.toString())
                }else{
                    Log.d("MYAPI","REPONSE IS NOT SUCCESSFUL")
                    //Log.d("MYAPI","${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("MYAPI","Error $t")
            }
        })
        }
}