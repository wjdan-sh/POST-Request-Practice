package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var view: Button
    private lateinit var ID: EditText
    private lateinit var name: EditText
    private lateinit var location: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        update = findViewById(R.id.UP)
        delete = findViewById(R.id.DE)
        view = findViewById(R.id.view1)
        ID = findViewById(R.id.id)
        name = findViewById(R.id.name)
        location = findViewById(R.id.location)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        update.setOnClickListener {
            val ID= ID.text.toString().toInt()
            val name= name.text.toString()
            val location= location.text.toString()

            apiInterface!!.updateUser( ID , Data1( ID ,name ,location)).enqueue(object:
                Callback<Data1>{
                override fun onResponse(call: Call<Data1>, response: Response<Data1>) {
                    Toast.makeText(this@MainActivity3, "user updated ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Data1>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "user not updated ", Toast.LENGTH_SHORT).show()
                }

            })
        }

        delete.setOnClickListener {
            val ID= ID.text.toString().toInt()
            apiInterface!!.deleteUser(ID).enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity3, "user deleted ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(this@MainActivity3, "user not deleted ", Toast.LENGTH_SHORT).show()
                }

            })

        }

        view.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }

    }
}