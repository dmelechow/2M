package com.dmelechow.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dmelechow.R
import com.dmelechow.presentation.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachFragment(HomeFragment.newInstance(), false)
    }

    fun attachFragment(fragment: Fragment, isWithBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container.id, fragment)

        if (isWithBackStack) {
            transaction
                .addToBackStack(null)
                .commit()
        } else {
            transaction
                .commit()
        }

    }
}
