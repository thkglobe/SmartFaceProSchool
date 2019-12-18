package com.app.facepro.faceproschool.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.facepro.faceproschool.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thkglobe.app.facepro.common.MenuVisibility
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.core.view.MenuItemCompat




class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()
    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }
    private var notificationCount="10"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        sharedViewModel.toolBarVisibilityObserver.observe(
            this@MainActivity,
            Observer { menuVisibility ->
                when (menuVisibility) {
                    MenuVisibility.TOOL_BAR -> {
                        navView.visibility = View.GONE
                        toolbar.visibility = View.VISIBLE
                    }
                    MenuVisibility.BOTTOM_BAR -> {
                        navView.visibility = View.VISIBLE
                        toolbar.visibility = View.GONE
                    }
                    MenuVisibility.BOTH -> {
                        navView.visibility = View.VISIBLE
                        toolbar.visibility = View.VISIBLE
                    }
                    else -> {
                        navView.visibility = View.GONE
                        toolbar.visibility = View.GONE
                    }
                }
            })
        sharedViewModel.notificationCountObserver.observe(this@MainActivity, Observer { count ->
            notificationCount = count;
            invalidateOptionsMenu()
        })

    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.menu_notification)
        MenuItemCompat.setActionView(item,R.layout.layout_notification_count)
        val view = MenuItemCompat.getActionView(item)
       val notifCount = view.findViewById(R.id.badge_textView) as TextView
        notifCount.text =notificationCount
        notifCount.setOnClickListener {
            navController?.navigate(R.id.navigation_notifications)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_notification -> {
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    companion object{
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
