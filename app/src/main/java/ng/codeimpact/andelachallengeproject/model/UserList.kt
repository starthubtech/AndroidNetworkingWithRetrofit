package ng.codeimpact.andelachallengeproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

class UserList {

    @SerializedName("items")
    @Expose
    var items: List<User>? = null
}