package com.glinyanov.childstars.ui.adapters

import android.annotation.SuppressLint


abstract class SimpleRVAdapter<T> : BaseRVAdapter<T>() {

    var items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    open fun addData(newItems: List<T>) {
        val prevSize = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(prevSize, newItems.size)
    }

    open fun addData(newItem: T) {
        val prevSize = items.size
        items.add(newItem)
        notifyItemInserted(prevSize)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}