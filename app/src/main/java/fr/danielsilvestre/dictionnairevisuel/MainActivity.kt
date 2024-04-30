package fr.danielsilvestre.dictionnairevisuel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import fr.danielsilvestre.dictionnairevisuel.rest.DirectusApi
import fr.danielsilvestre.dictionnairevisuel.rest.DirectusResponse
import fr.danielsilvestre.dictionnairevisuel.rest.models.Categorie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
    }

    override fun onStart() {
        super.onStart()
        val service = DirectusApi.getInstance().getService()
        val call = service.listCategories()
        call?.enqueue(object : Callback<DirectusResponse<List<Categorie>>> {
            override fun onResponse(
                call: Call<DirectusResponse<List<Categorie>>>,
                response: Response<DirectusResponse<List<Categorie>>>
            ) {
                val categories: DirectusResponse<List<Categorie>>? = response.body()
                if (categories?.data != null) {
                    for (item in categories.data!!) {
                        if(item.label != null)
                        Log.i("DIRECTUS_API_RESPONSE", item.label!!)
                    }
                }

            }

            override fun onFailure(call: Call<DirectusResponse<List<Categorie>>>, t: Throwable) {

                Log.i("DIRECTUS_API_RESPONSE", t.message.toString())
            }
        })
    }
}