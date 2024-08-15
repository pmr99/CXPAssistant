package com.google.ai.cxpAssistant
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class PostData(
    val message: String,
    val remove: String,
)

interface ApiService {
    @POST("/post")
    fun sendPost(@Body postData: PostData): Call<ResponseBody>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
