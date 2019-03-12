package ng.codeimpact.andelachallengeproject.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

class User protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null


    init {
        login = `in`.readString()
        avatarUrl = `in`.readString()
        gravatarId = `in`.readString()
        url = `in`.readString()
        htmlUrl = `in`.readString()


    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(login)
        dest.writeString(avatarUrl)
        dest.writeString(gravatarId)
        dest.writeString(url)
        dest.writeString(htmlUrl)


    }

    companion object {

        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(`in`: Parcel): User {
                return User(`in`)
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOfNulls(size)
            }
        }
    }
}