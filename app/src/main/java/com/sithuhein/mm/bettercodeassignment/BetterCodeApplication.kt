package com.sithuhein.mm.bettercodeassignment

import android.app.Application
import com.sithuhein.mm.bettercodeassignment.di.AppComponent
import com.sithuhein.mm.bettercodeassignment.di.DaggerAppComponent

class BetterCodeApplication : Application() {

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}