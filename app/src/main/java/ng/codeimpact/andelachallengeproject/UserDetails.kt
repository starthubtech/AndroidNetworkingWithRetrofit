package ng.codeimpact.andelachallengeproject

import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.util.Linkify
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import org.w3c.dom.Text

import me.saket.bettermovementmethod.BetterLinkMovementMethod
import ng.codeimpact.andelachallengeproject.model.User
import ng.codeimpact.andelachallengeproject.util.ShareUtils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class UserDetails : AppCompatActivity() {

    private var user_name_details: TextView? = null
    private var user_github_url: TextView? = null
    private var user_avatar: ImageView? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    private val customTabIntentInstance: CustomTabsIntent
        get() {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
            return builder.build()
        }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detials)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { shareProfile() }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        collapsingToolbarLayout = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        collapsingToolbarLayout!!.title = ""

        user_avatar = collapsingToolbarLayout!!.findViewById<View>(R.id.user_avatar) as ImageView
        user_name_details = findViewById<View>(R.id.user_name_details) as TextView
        user_github_url = findViewById<View>(R.id.github_profile_url) as TextView

        //getting intent extra



        //loading imageurl with Glide
        //Glide.with(applicationContext).load(user.avatarUrl).into(user_avatar!!)

        val movementMethod = BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, user_github_url!!)
        movementMethod.setOnLinkClickListener { textView, url ->
            customTabIntentInstance.launchUrl(this@UserDetails, Uri.parse(url))
            true
        }
    }

    private fun shareProfile() {

       // val user = intent.getParcelableExtra<User>("user")


        //val message = "Check out this awesome developer @" + user.login + ", " + user.url

       // ShareUtils.shareCustom(message, this)

    }
}
