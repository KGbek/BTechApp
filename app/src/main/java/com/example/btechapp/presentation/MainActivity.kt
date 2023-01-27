package com.example.btechapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.btechapp.R
import com.example.btechapp.databinding.ActivityMainBinding
import com.example.btechapp.presentation.fragments.cart.CartFragment
import com.example.btechapp.presentation.fragments.home.HomeFragment
import com.example.btechapp.presentation.fragments.news.NewsFragment
import com.example.btechapp.presentation.fragments.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        loadFragment(HomeFragment())
        bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.newsFragment2 -> {
                    loadFragment(NewsFragment())
                true
                }
                R.id.cartFragment2 -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        transaction.commit()
    }

}