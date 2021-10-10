package com.example.postrequestpractice

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var RV: RecyclerView
    private lateinit var btn: Button
    private lateinit var users: ArrayList<String>

    lateinit var data:SharedPreferences
    lateinit var editr :SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<TestList?>> = apiInterface!!.doGetListResources()

        btn = findViewById(R.id.add)
        RV = findViewById(R.id.rc)
        users = ArrayList()

        RV.adapter = RecyclerViewAdapter(users)
        RV.layoutManager = LinearLayoutManager(this)


        data=getSharedPreferences("users", MODE_PRIVATE)
        editr=data.edit()

        call?.enqueue(object : Callback<List<TestList?>> {
            override fun onResponse(
                call: Call<List<TestList?>>,
                response: Response<List<TestList?>>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: List<TestList?>? = response.body()
                if (resource != null) {
                    for(user in resource) {
                        val username = user?.name
                        val userloc = user?.location
                        users.add(username.toString())
                        users.add(userloc.toString())
                        RV.adapter?.notifyDataSetChanged()
                        RV.scrollToPosition(users.size-1)
                    }
                }

            }

            override fun onFailure(call: Call<List<TestList?>>, t: Throwable?) {
                call.cancel()
            }
        })

        btn.setOnClickListener {

            val intent = Intent(this, MainActivity2 ::class.java)
            startActivity(intent);
        }


    }

    }
