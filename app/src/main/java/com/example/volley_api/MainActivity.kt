package com.example.volley_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val url_link = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiate our volley request
        volleyRequest = Volley.newRequestQueue(this)

        //calling our method
        fetchStringData(url_link)

    }

    fun fetchStringData(url:String) {
        val stringReq = StringRequest(Request.Method.GET,url, {
                response:String? ->
            try {
                //Log our response to our terminal
                 Log.d("Response ", response.toString())

            } catch (e:JSONException) {
                e.printStackTrace()
            }

        },
            {
                 //error listener
                error:VolleyError? ->
                try {
                        Log.d("Error", error.toString())

                } catch (e:JSONException) {
                      e.printStackTrace()

                }
            })

        //adding our request queue
        volleyRequest!!.add(stringReq)
    }
}