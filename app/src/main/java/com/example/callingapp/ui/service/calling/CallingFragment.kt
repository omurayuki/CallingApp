package com.example.callingapp.ui.service.calling

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import javax.inject.Inject

/**
 * 呼び出し中画面
 */
class CallingFragment: Fragment(R.layout.calling_fragment), Injected {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CallingViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.finish.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_calling_to_done)
        }
    }
}
