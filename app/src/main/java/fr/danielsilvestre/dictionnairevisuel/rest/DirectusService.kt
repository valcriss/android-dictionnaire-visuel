package fr.danielsilvestre.dictionnairevisuel.rest

import fr.danielsilvestre.dictionnairevisuel.rest.models.Categorie
import retrofit2.Call
import retrofit2.http.GET

interface DirectusService {
    @GET("items/categorie")
    fun listCategories(): Call<DirectusResponse<List<Categorie>>>
}