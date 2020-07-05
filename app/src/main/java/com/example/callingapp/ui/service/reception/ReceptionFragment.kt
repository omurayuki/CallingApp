package com.example.callingapp.ui.service.reception

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import kotlinx.android.synthetic.main.reception_fragment.*
import javax.inject.Inject

/**
 * 総合受付 訪問者情報入力画面
 */
class ReceptionFragment : Fragment(R.layout.reception_fragment), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ReceptionViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener { requireActivity().onBackPressed() }
        calling_button.setOnClickListener {
            findNavController().navigate(R.id.action_reception_to_calling)
        }

        corporate_name_edit_text.doOnTextChanged { text, start, count, after ->
            viewModel.corporateName.value = text?.toString()
        }
        name_edit_text.doOnTextChanged { text, start, count, after ->
            viewModel.name.value = text?.toString()
        }
        purpose_edit_text.doOnTextChanged { text, start, count, after ->
            viewModel.purpose.value = text?.toString()
        }

        viewModel.enable.observe(viewLifecycleOwner) {
            calling_button.isEnabled = it
        }
    }
}