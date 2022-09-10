package com.example.volley_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val url_link = "https://jsonplaceholder.typicode.com/users"
    val movie_array_url = "http://api.tvmaze.com/search/shows?q=golden%20girls"
    val obj_url = "https://cryptingup.com/api/markets"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiate our volley request
        volleyRequest = Volley.newRequestQueue(this)

        //calling our method
        //fetchStringData(url_link)
        //fetchJsonArray(movie_array_url)
        fetchJsonObject(obj_url)

    }

    fun fetchJsonObject(url_link_object:String) {

         var jsonObjReq = JsonObjectRequest(Request.Method.GET, url_link_object, null, Response.Listener<JSONObject> {
                          response ->
             try {

                 var next = response.getString("markets")
                 Log.d("Response on markets", next.toString() )

             } catch (e: JSONException) {
                 Log.d("Error-->" , e.toString())
             }
         },
         Response.ErrorListener {

             try {

             } catch (e: JSONException) {
                 Log.d("Error-->" , e.toString())
             }
         })


        volleyRequest!!.add(jsonObjReq)
    }


        //volleyRequest!!.add(jsonobject)






    fun fetchJsonArray(Url:String) {

        val arrayReq = JsonArrayRequest(Request.Method.GET, Url,null,
            {
                    response: JSONArray ->
                try {
                    Log.d("Response-->" , response.toString())

                    //Now accessing the json object inside our array
                    // range replaced with until unlike 0..response.length()
                    for (x in 0 until response.length()) {
                        //create a variable to loop to our objects
                        var showObj = response.getJSONObject(x)
                        var get_show = showObj.getString("show")

                        //Log our object response
                        //Log.d("GET Show", get_show.toString())

                        //show name of all movies showing
//                        var movie_name = showObj.getString("name")
//                        Log.d(" Show Name" , movie_name.toString())


//                        for (i in 0 until get_score.toInt()) {
//                            var score_obj = response.getJSONObject(i)
//
//
//                        }



                    }


                } catch (e:JSONException) {

                    e.printStackTrace()

                }
            },
            {
                    error:VolleyError? ->
                try {

                    Log.d("Error-->" , error.toString())

                } catch (e:JSONException) {
                    e.printStackTrace()

                }

            })

        volleyRequest!!.add(arrayReq)




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