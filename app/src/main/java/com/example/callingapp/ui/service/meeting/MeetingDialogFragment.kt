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
import kotlinx.android.synthetic.main.meeting_dialog_fragment.view.*
import javax.inject.Inject

/**
 * 打ち合わせ訪問者情報入力ダイアログ
 */
class MeetingDialogFragment : DialogFragment(), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MeetingCallViewModel> { viewModelFactory }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.meeting_dialog_fragment, null).apply {
                calling_button.setOnClickListener {
                    val member = arguments?.getSerializable("member")
                    if (member is Member) {
                        val name = this.name_edit.text.toString()
                        val number = this.number_edit.text.toString()
                        val org = this.org_edit.text.toString().let {
                            if (it.isNotEmpty()) "${it}の" else ""
                        }
                        viewModel.call(
                            "<!here> @${member.slackName} ${org}${name}さんがお見えになられました。人数は${number}人です。"
                        )
                        findNavController().navigate(R.id.action_meeting_dialog_to_calling)
                    }
                }
            }

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }
}

