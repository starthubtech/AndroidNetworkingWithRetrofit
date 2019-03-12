package ng.codeimpact.andelachallengeproject

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import ng.codeimpact.andelachallengeproject.adapter.UserAdapter
import ng.codeimpact.andelachallengeproject.model.UserResponse
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
    lateinit var pd: ProgressDialog

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

        pd = ProgressDialog(this).apply {
            setMessage("Fetching Users")
            setCancelable(false)
        }
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

        if(isNetworkAvailable){
            fetchUsersData()
        }


    }

    private fun prepareData(userResponse: UserResponse) {
        adapter = UserAdapter(userResponse.items!!)
        recyclerView!!.adapter = adapter

    }


    //TODO("Call the API service to fetch the users data 9 ")
    private fun fetchUsersData() {
        pd.showProgress()
        val searchParams = "language:java location:uyo"
        val apiService = RestApiBuilder().RestApiService()
        apiService.getUsers(searchParams).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                pd.hideProgress()
                val toast = Toast(this@MainActivity).apply {
                    setText("Request Failed " + t.message)
                    duration = Toast.LENGTH_SHORT
                    show()
                }
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                pd.hideProgress()
                val users = response.body()
                prepareData(users!!)
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
