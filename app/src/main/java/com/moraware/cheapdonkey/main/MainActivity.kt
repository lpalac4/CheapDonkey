package com.moraware.cheapdonkey.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import com.moraware.cheapdonkey.R
import com.moraware.cheapdonkey.base.BaseActivity
import com.moraware.cheapdonkey.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainActivityViewModel>() {
    private lateinit var binding: ActivityMainBinding

    override fun setupUIAndBindViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mViewModel
    }
}
