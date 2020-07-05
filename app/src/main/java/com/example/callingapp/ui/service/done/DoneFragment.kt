package com.example.callingapp.ui.service.done

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import javax.inject.Inject

/**
 * 呼び出し完了画面
 */
class DoneFragment : Fragment(R.layout.done_fragment), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DoneViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.finish.observe(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.navigation_home, false)
        }
    }
}
