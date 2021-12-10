package com.test.randomuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.randomuser.R
import com.test.randomuser.databinding.ActivityMainBinding
import com.test.randomuser.ui.fragments.UserListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //creating fragment object
    var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize fragment
        fragment = UserListFragment.newInstance()
        transactFragment(fragment)
    }

    fun transactFragment(fragment: Fragment?){
        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.main_container, fragment)
            ft.commit()
        }
    }
}