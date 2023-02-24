package com.example.btechapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.btechapp.R
import com.example.btechapp.databinding.ActivityMainBinding
import com.example.btechapp.presentation.fragments.cart.CartFragment
import com.example.btechapp.presentation.fragments.home.HomeFragment
import com.example.btechapp.presentation.fragments.news.NewsFragment
import com.example.btechapp.presentation.fragments.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
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
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}