package com.example.callingapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment: Fragment(R.layout.main_fragment), Injected {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_login)
        }

        reception.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_reception)
        }

        appointment.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_meeting, bundleOf("type" to MeetingFragment.TYPE_APPOINTMENT))
        }

        jobInterview.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_job_inetrview, bundleOf("type" to MeetingFragment.TYPE_JOB_MEETING))
        }

        delivery.setOnClickListener {
            Toast.makeText(requireContext(), "Tap a Delivery button", Toast.LENGTH_SHORT).show()
        }
    }
}