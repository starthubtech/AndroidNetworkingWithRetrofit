package ng.codeimpact.andelachallengeproject.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

class RestApiBuilder {

    private val retrofit: Retrofit

    val service: RestApiService
        get() = retrofit.create(RestApiService::class.java)

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    companion object {

        val BASE_URL = "https://api.github.com"
    }
}