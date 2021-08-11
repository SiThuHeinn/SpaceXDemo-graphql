package com.sithuhein.mm.bettercodeassignment.di

import android.content.Context
import com.sithuhein.mm.bettercodeassignment.ui.MainActivity
import com.sithuhein.mm.bettercodeassignment.ui.fragments.LaunchDetailsFragment
import com.sithuhein.mm.bettercodeassignment.ui.fragments.LaunchesFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    //... to pass context from out of dependency graph
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(launchesFragment: LaunchesFragment)
    fun inject(launchDetailsFragment: LaunchDetailsFragment)

}