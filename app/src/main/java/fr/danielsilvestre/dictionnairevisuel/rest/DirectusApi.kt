package fr.danielsilvestre.dictionnairevisuel.rest

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DirectusApi() {
    companion object {
        const val BASE_URL = "https://dicvis.danielsilvestre.fr/"
        const val TOKEN = "qwItWQ1DgxEgxLYZ4O9muuc42acZfGCo";

        @Volatile
        private var instance: DirectusApi? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DirectusApi().also { instance = it }
        }
    }

    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .build()
        chain.proceed(newRequest)
    }.build()
    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private var service: DirectusService = retrofit.create(DirectusService::class.java)

    fun getService(): DirectusService {
        return service
    }
}