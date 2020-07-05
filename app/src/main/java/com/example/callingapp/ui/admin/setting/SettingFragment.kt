package com.example.callingapp.ui.admin.setting

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import com.example.callingapp.ext.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.setting_fragment.*
import timber.log.Timber
import javax.inject.Inject

class SettingFragment : Fragment(R.layout.setting_fragment), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SettingViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        password_edit_text.doOnTextChanged { text, _, _, _ ->
            viewModel.newPassword = text.toString()
        }

        slack_channel_edit_text.doOnTextChanged { text, _, _, _ ->
            viewModel.newSlackChannel = text.toString()
        }

        slack_channel_input_layout.hint = "${getString(R.string.setting_slack_channel)} (${viewModel.currentSlackChannel})"

        edit_complete_button.setOnClickListener {
            viewModel.save()
            Snackbar.make(admin_setting, R.string.setting_edit_complete, Snackbar.LENGTH_SHORT).show()
        }

        back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        Timber.d("$viewModel")
    }
}
