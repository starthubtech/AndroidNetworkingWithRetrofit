package ng.codeimpact.andelachallengeproject.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.bumptech.glide.Glide

import de.hdodenhof.circleimageview.CircleImageView
import ng.codeimpact.andelachallengeproject.R
import ng.codeimpact.andelachallengeproject.UserDetails
import ng.codeimpact.andelachallengeproject.model.User

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var user_profile_avatar: CircleImageView
        internal var username: TextView
        internal var user_state: TextView


        init {
            user_profile_avatar = view.findViewById<View>(R.id.profile_image) as CircleImageView
            username = view.findViewById<View>(R.id.user_name) as TextView
            user_state = view.findViewById<View>(R.id.userstate) as TextView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.userlist_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]

        holder.username.text = user.login
        holder.user_state.text = "Lagos"


        //Loading the image using Glide
        val context = holder.user_profile_avatar.context
        Glide.with(context).load(user.avatarUrl).into(holder.user_profile_avatar)


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UserDetails::class.java)
            intent.putExtra("user", user)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
