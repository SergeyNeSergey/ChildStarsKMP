package com.glinyanov.childstars.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseRVAdapter<T> : RecyclerView.Adapter<BaseRVAdapter<T>.BaseViewHolder>() {

    open inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bindView(item: T) {
            //to implement
        }

        open fun bindView() {
            //to implement
        }
    }
}