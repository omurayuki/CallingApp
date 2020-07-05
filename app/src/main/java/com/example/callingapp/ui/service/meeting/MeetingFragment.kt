package com.example.callingapp.ui.service.meeting

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.callingapp.R
import com.example.callingapp.di.Injected
import com.example.callingapp.widget.MembersListItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.meeting_fragment.*
import javax.inject.Inject

/**
 * 打ち合わせ訪問先選択画面
 */
class MeetingFragment : Fragment(R.layout.meeting_fragment), Injected {

    companion object {
        const val TYPE_APPOINTMENT = 1
        const val TYPE_JOB_MEETING = 2
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MeetingViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GroupAdapter<ViewHolder>()
        list.adapter = adapter

        val receivedValue = arguments?.getInt("type")

        viewModel.membersList.observe(viewLifecycleOwner) { list ->
            adapter.update(list.map {
                MembersListItem(
                    it
                )
            })
        }

        adapter.setOnItemClickListener { item, _ ->
            val param = bundleOf("member" to (item as MembersListItem).member)
            when (receivedValue) {
                TYPE_APPOINTMENT -> {
                    findNavController().navigate(R.id.action_meeting_to_meeting_dialog, param)
                }
                TYPE_JOB_MEETING -> {
                    findNavController().navigate(R.id.action_meeting_to_job_interview_dialog, param)
                }
            }
        }

        back_button.setOnClickListener { findNavController().popBackStack() }

        viewModel.fetch()
    }
}
