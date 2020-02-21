package com.dmelechow.presentation.searh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmelechow.data.model.Artist
import com.dmelechow.presentation.base.viewholder.ArtistListenerAdapter
import com.dmelechow.presentation.base.viewholder.ArtistViewHolder


class SearchArtistAdapter(
    private val artistListenerAdapter: ArtistListenerAdapter
) : RecyclerView.Adapter<ArtistViewHolder>() {

    private var items: MutableList<Artist> = ArrayList()

    fun notifyItems(items: List<Artist>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                ArtistViewHolder.layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.setListener(artistListenerAdapter)
        holder.bindViews(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}