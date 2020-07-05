package com.example.callingapp.ui.admin.login

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
import com.example.callingapp.ext.hideKeyboard
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.login_fragment), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        password_edit_text.doOnTextChanged { text, _, _, _ ->
            viewModel.password.value = text?.toString()
        }

        viewModel.enable.observe(viewLifecycleOwner) {
            login_button.isEnabled = it
        }

        login_button.setOnClickListener {
            // ログイン判定、画面遷移
            if (viewModel.testPassword()) {
                Timber.d("login")
                findNavController().navigate(R.id.action_login_to_top)
            }
        }
    }
}