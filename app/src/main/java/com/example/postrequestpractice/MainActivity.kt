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
    private lateinit var UD: Button
    private lateinit var users: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<TestList?>> = apiInterface!!.doGetListResources()

        btn = findViewById(R.id.add)
        UD = findViewById(R.id.UD)
        RV = findViewById(R.id.rc)
        users = ArrayList()

        RV.adapter = RecyclerViewAdapter(users)
        RV.layoutManager = LinearLayoutManager(this)



        call?.enqueue(object : Callback<List<TestList?>> {
            override fun onResponse(
                call: Call<List<TestList?>>,
                response: Response<List<TestList?>>
            ) {
                val resource: List<TestList?>? = response.body()
                if (resource != null) {
                    for(user in resource) {
                        val userid = user?.pk.toString()
                        val username = user?.name.toString()
                        val userloc = user?.location.toString()

                        users.add( userid +"\n"+ username +"\n"+ userloc )

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

        UD.setOnClickListener {

            val intent = Intent(this, MainActivity3 ::class.java)
            startActivity(intent);
        }


    }

    }
