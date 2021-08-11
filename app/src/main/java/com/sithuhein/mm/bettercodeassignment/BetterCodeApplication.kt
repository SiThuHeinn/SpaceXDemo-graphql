package com.sithuhein.mm.bettercodeassignment

import android.app.Application
import com.sithuhein.mm.bettercodeassignment.di.AppComponent
import com.sithuhein.mm.bettercodeassignment.di.DaggerAppComponent

class BetterCodeApplication : Application() {

    //... applicationContext is passed here when Dagger create dependencies graph for us
    //... applicationContext is injected by Dagger itself whenever require.
    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}