package com.example.network_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager : LinearLayoutManager
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView_users)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("MainActivity","onFailure"+t.message)
            }
        })
    }
}