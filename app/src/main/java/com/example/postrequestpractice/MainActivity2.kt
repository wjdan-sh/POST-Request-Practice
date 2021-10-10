package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var save: Button
    private lateinit var view: Button
    private lateinit var name: EditText
    private lateinit var location: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        save = findViewById(R.id.save)
        view = findViewById(R.id.view)
        name = findViewById(R.id.name)
        location = findViewById(R.id.location)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        save.setOnClickListener {

            val name= name.text.toString()
            val location= location.text.toString()

            apiInterface!!.adduser(Data( name ,location)).enqueue(object: Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    Toast.makeText(this@MainActivity2, "user added ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Toast.makeText(this@MainActivity2, "user not added ", Toast.LENGTH_SHORT).show()
                }

            })

        }

        view.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }


    }
}