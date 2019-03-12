package ng.codeimpact.andelachallengeproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */
//TODO("Create POJO for the full JSON response 8")
data class UserResponse (
    @SerializedName("items")
    @Expose
    var items: List<User>? = null
)