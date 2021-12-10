package com.test.randomuser.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.test.randomuser.R
import com.test.randomuser.data.model.User
import com.test.randomuser.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.title = "User Detail"
        }

        val user: User = intent.getParcelableExtra(USER) ?: return

        Glide.with(this).load(user.picture)
            .circleCrop()
            .into(binding.imageView)

        binding.name.text = "${user.first_name} ${user.last_name}"
    }

    companion object {
        private const val USER = "user"

        fun startActivity(context: Context?, user: User) = context?.startActivity(
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(USER, user)
            }
        )
    }
}