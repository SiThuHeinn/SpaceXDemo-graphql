package com.sithuhein.mm.bettercodeassignment.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sithuhein.mm.bettercodeassignment.BetterCodeApplication
import com.sithuhein.mm.bettercodeassignment.R
import com.sithuhein.mm.bettercodeassignment.ui.MainViewModel
import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.resultState.ResultStatus
import kotlinx.android.synthetic.main.fragment_launch_details.*
import kotlinx.android.synthetic.main.fragment_launches.*
import javax.inject.Inject


class LaunchDetailsFragment : Fragment(R.layout.fragment_launch_details) {

    @Inject lateinit var viewModel : MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BetterCodeApplication).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiListener()
    }

    private fun uiListener() {
        viewModel.launchId.observe(viewLifecycleOwner) { id ->
            clearRecentData()
            viewModel.getLaunchDetails(id)
        }
        viewModel.launchDetails.observe(viewLifecycleOwner) { result ->
            clearRecentData()
            render(result)
        }
    }

    private fun render(result : ResultStatus<LaunchDetails>) {
        when (result) {
            is ResultStatus.Loading -> { showLoading() }
            is ResultStatus.Success -> {
                hideLoading()
                setData(result.data)
            }
            is ResultStatus.Error -> {
                hideLoading()
            }
            is ResultStatus.NetworkConnection -> {
                hideLoading()
            }
        }
    }


    private fun showLoading() {
        loading_details.visibility = View.VISIBLE
    }
    private fun hideLoading() {
        loading_details.visibility = View.GONE
    }

    private fun clearRecentData() {
        details_img.setImageResource(R.drawable.ic_undraw_placeholder)
        mission_name.text = ""
        mission_launch_year.text = ""
        launch_success.text = ""
        article_link.text = ""
        wikipedia_link.text = ""
    }

    private fun setData(launchDetails : LaunchDetails?) {
        mission_name.text = launchDetails?.mission_name ?: ""
        mission_launch_year.text = launchDetails?.launch_year ?: ""
        launch_success.text = launchDetails?.launch_success.toString()
        article_link.text = launchDetails?.links?.article_link ?: ""
        wikipedia_link.text = launchDetails?.links?.wikipedia ?: ""

        Glide.with(requireContext())
            .load(launchDetails?.links?.mission_patch)
            .placeholder(R.drawable.ic_undraw_placeholder)
            .into(details_img)
    }
}