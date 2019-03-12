package ng.codeimpact.andelachallengeproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.view.animation.GridLayoutAnimationController
import android.widget.Toast

import ng.codeimpact.andelachallengeproject.adapter.UserAdapter
import ng.codeimpact.andelachallengeproject.model.UserList
import ng.codeimpact.andelachallengeproject.service.RestApiBuilder
import ng.codeimpact.andelachallengeproject.service.RestApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: UserAdapter? = null
    private var coordinatorLayout: CoordinatorLayout? = null

    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        coordinatorLayout = findViewById<View>(R.id.coordinator_layout) as CoordinatorLayout
        recyclerView = findViewById<View>(R.id.recycler_user_list) as RecyclerView
        val layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = layoutManager


        //checking for network connectivity
        if (!isNetworkAvailable) {
            val snackbar = Snackbar
                    .make(coordinatorLayout!!, "No Network connection", Snackbar.LENGTH_LONG)
                    .setAction("RETRY") { fetchUsersData() }

            snackbar.show()
        } else {
            fetchUsersData()
        }


    }


    private fun prepareData(userList: UserList) {
        adapter = UserAdapter(userList.items!!)
        recyclerView!!.adapter = adapter

    }


    private fun fetchUsersData() {
        val searchParams = "language:java location:lagos"
        val apiService = RestApiBuilder().service
        val userListCall = apiService.getUserList(searchParams)
        userListCall.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    prepareData(userList!!)
                } else {

                    Toast.makeText(this@MainActivity,
                            "Request not Sucessful",
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
