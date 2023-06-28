package com.kabiplayer.screens

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.kabiplayer.R
import com.kabiplayer.databinding.ActivityBottomNavBinding

class BottomNavActivity : BaseActivity() {
    private lateinit var binding: ActivityBottomNavBinding

    // Flags to know whether bottom tab fragments are displayed at least once
    private val fragmentFirstDisplay = mutableListOf(false, false, false)

    private val feedFragment = FeedFragment()

    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = feedFragment

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        fragmentManager.beginTransaction().apply {
            add(R.id.container, feedFragment, "home")
        }.commit()


        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    fragmentManager.beginTransaction().hide(activeFragment)
                        .show(feedFragment).commit()
                    activeFragment = feedFragment
                    true
                }

                else -> false
            }
        }
    }


    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is FeedFragment) {
//            fragmentFirstDisplay[0] = true
//            fragment.onFirstDisplay()
        }
    }


}