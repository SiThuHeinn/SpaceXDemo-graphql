package com.sithuhein.mm.bettercodeassignment.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.sithuhein.mm.bettercodeassignment.BetterCodeApplication
import com.sithuhein.mm.bettercodeassignment.R
import com.sithuhein.mm.bettercodeassignment.adapter.LaunchesListAdapter
import com.sithuhein.mm.bettercodeassignment.ui.MainViewModel
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.resultState.ResultStatus
import kotlinx.android.synthetic.main.fragment_launches.*
import javax.inject.Inject


class LaunchesFragment : Fragment(R.layout.fragment_launches) {


    @Inject lateinit var viewModel : MainViewModel
    @Inject lateinit var adapter : LaunchesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BetterCodeApplication).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        uiListener(view)
    }

    private fun uiListener(view : View) {
        viewModel.launchesPastList.observe(viewLifecycleOwner) { result ->
            render(result)
        }
        
        btnRetry.setOnClickListener {
            viewModel.getLaunchesPastList()
        }

        adapter.setItemClickListener {
            viewModel.setLaunchId(it.id.toString())
            navigateToDetailsScreen(view)
        }
    }

    private fun setupUi() {
        launches_recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        launches_recyclerView.adapter = adapter

    }

    private fun render(result : ResultStatus<List<LaunchesPast>>) {
        when (result) {
            is ResultStatus.Loading -> {
                showLoading()
                hideErrorMessage()
            }
            is ResultStatus.Success -> {
                hideLoading()
                hideErrorMessage()
                adapter.launchesPast = result.data ?: listOf()
                result.data?.forEach { Log.d("MainActivity ", "${it.links?.mission_patch}}") }
            }
            is ResultStatus.Error -> {
                hideLoading()
                showErrorMessage(result.message ?: "")
            }
            is ResultStatus.NetworkConnection -> {
                hideLoading()
                showRetryButton()
                showErrorMessage(result.message ?: "")
            }
        }
    }

    private fun navigateToDetailsScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.navigateToLaunchDetailsFragment)
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
        hideRetryButton()
    }
    private fun hideLoading() {
        loading.visibility = View.GONE
        hideRetryButton()
    }

    private fun showErrorMessage(error : String = "") {
        error_message.visibility = View.VISIBLE
        error_message.text = error
    }
    private fun hideErrorMessage(error : String = "") {
        error_message.text = error
        error_message.visibility = View.GONE
        hideRetryButton()
    }

    private fun showRetryButton() {
        btnRetry.visibility = View.VISIBLE
    }
    private fun hideRetryButton() {
        btnRetry.visibility = View.GONE
    }
}