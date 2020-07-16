package com.example.callingapp.ui.admin.top

import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.top_fragment.*
import timber.log.Timber
import javax.inject.Inject

class TopFragment : Fragment(R.layout.top_fragment), Injected {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sync_button.setOnClickListener {
            // 社員一覧の同期をする
            // ?社員一覧同期画面へ
            Timber.d("fetch members list")
            viewModel.fetch()
        }

        back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = GroupAdapter<ViewHolder>()
        members_list.adapter = adapter

        viewModel.membersList.observe(viewLifecycleOwner) { list ->
            adapter.update(list.map { MembersListItem(it) })
        }

        viewModel.fetch()
    }
}