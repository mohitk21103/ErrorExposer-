package mk.project.errorexposer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://api.stackexchange.com/2.3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val soResponses = retrofit.create(ApiService :: class.java)


interface ApiService {
    @GET("questions?order=desc&sort=activity&tagged=android&site=stackoverflow")
    suspend fun getSOResponse(): StackOverflowResponse
}