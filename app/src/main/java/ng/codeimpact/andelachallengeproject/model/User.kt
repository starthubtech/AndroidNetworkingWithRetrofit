package ng.codeimpact.andelachallengeproject.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */
//TODO("Create POJO for the response object 6")

data class User(
        @SerializedName("login")
        var login: String,
        @SerializedName("id")
        @Expose
        var id: Int,
        @SerializedName("avatar_url")
        @Expose
        var avatarUrl: String,
        @SerializedName("gravatar_id")
        @Expose
        var gravatarId: String? = null,
        @SerializedName("url")
        @Expose
        var url: String,
        @SerializedName("html_url")
        @Expose
        var htmlUrl: String
)


