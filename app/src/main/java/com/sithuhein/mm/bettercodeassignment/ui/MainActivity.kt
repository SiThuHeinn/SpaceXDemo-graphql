package com.sithuhein.mm.bettercodeassignment.ui

import com.sithuhein.mm.bettercodeassignment.BetterCodeApplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sithuhein.mm.bettercodeassignment.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //... ask dagger to inject dependencies
        //... It's a best practice to call inject before super.onCreate
        (application as BetterCodeApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}