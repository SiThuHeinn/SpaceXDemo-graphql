package com.sithuhein.mm.bettercodeassignment.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sithuhein.mm.bettercodeassignment.BetterCodeApplication
import com.sithuhein.mm.bettercodeassignment.R


class LaunchDetailsFragment : Fragment(R.layout.fragment_launch_details) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BetterCodeApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}