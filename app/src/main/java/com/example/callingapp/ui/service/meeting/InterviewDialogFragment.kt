package com.example.callingapp.ui.service.meeting

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import com.example.model.Member
import kotlinx.android.synthetic.main.interview_dialog_fragment.view.*
import javax.inject.Inject

/**
 * 面談担当者選択画面
 */
class InterviewDialogFragment : DialogFragment(), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MeetingCallViewModel> { viewModelFactory }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.interview_dialog_fragment, null).apply {
                this.calling_button.setOnClickListener {
                    val member = arguments?.getSerializable("member")
                    if (member is Member) {
                        val name = this.name_edit.text.toString()
                        viewModel.call(
                            "<!here> @${member.slackName} ${name}さんがお見えになられました。"
                        )
                        findNavController().navigate(R.id.action_job_interview_dialog_to_calling)
                    }
                }
            }

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }
}
