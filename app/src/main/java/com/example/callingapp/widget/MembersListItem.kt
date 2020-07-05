package com.example.callingapp.widget

import com.example.callingapp.R
import com.example.model.Member
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.member_item.*

class MembersListItem(
    val member: Member
) : Item() {

    override fun getLayout(): Int = R.layout.member_item

    override fun bind(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.apply {
            name.text = member.name
            kana.text = member.kana
            division.text = member.division
            title.text = member.title
        }
    }
}
