package ng.codeimpact.andelachallengeproject.service

import ng.codeimpact.andelachallengeproject.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */


interface RestApiService {

    //TODO("Add request endpoint 2)
    @GET("search/users")
    fun getUsers(@Query("q") query: String): Call<UserResponse>


}
