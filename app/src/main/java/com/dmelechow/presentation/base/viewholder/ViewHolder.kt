package com.dmelechow.presentation.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindViews(data: T)
}