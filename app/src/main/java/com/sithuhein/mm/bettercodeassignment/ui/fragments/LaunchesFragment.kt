package com.sithuhein.mm.bettercodeassignment.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        uiListener()
    }

    private fun uiListener() {
        viewModel.launchesPastList.observe(viewLifecycleOwner) { result ->
            render(result)
        }
    }

    private fun setupUi() {
        //TODO -> set up recycler view

        adapter.setItemClickListener {

        }
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
            }
            is ResultStatus.Error -> {
                hideLoading()
                showErrorMessage(result.message ?: "")
            }
            is ResultStatus.NetworkConnection -> {
                hideLoading()
                showErrorMessage(result.message ?: "")
            }
        }
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }
    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showErrorMessage(error : String = "") {
        error_message.visibility = View.VISIBLE
        error_message.text = error
    }
    private fun hideErrorMessage(error : String = "") {
        error_message.text = error
        error_message.visibility = View.GONE
    }
}