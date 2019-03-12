package ng.codeimpact.andelachallengeproject.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

public class RestApiBuilder {

   // TODO("Add Base Url 3")
    public static final String BASE_URL = "https://api.github.com";

    private Retrofit retrofit;

    // TODO("Create RestApiBuilder 4")
    public RestApiBuilder() {

    }

    // TODO("Create API service 5")
    public RestApiService getService() {

        return null ;
    }
}