package com.glinyanov.childstars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.request.ExposedUser

class ChildRVAdapter : SimpleRVAdapter<ExposedUser>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_child_item, parent, false)
        return ChildItemViewHolder(view)
    }

    fun setNewData(newItems: List<ExposedUser>) {
        super.setData(newItems)
    }

    var onChildClicked: ((city: ExposedUser) -> Unit)? = null

    inner class ChildItemViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private var childTv: TextView? = itemView.findViewById(R.id.childNameTv)

        override fun bindView(item: ExposedUser) {
            childTv?.text = item.name

            itemView.setOnClickListener { onChildClicked?.invoke(item) }
        }
    }
}