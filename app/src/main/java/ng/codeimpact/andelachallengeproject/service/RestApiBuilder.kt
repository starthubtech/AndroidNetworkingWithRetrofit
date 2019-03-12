package ng.codeimpact.andelachallengeproject.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */


class RestApiBuilder {

    private var retrofit: Retrofit? = null

    // TODO("Create RestApiBuilder 4")
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    // TODO("Create API service 5")
    fun RestApiService() = retrofit!!.create(RestApiService::class.java)

    companion object {
        // TODO("Add Base Url 3")
        @JvmField
        val BASE_URL = "https://api.github.com/"
    }
}