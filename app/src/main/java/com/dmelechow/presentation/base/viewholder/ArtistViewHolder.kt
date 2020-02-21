package com.dmelechow.presentation.base.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmelechow.R
import com.dmelechow.data.model.Artist
import kotlinx.android.synthetic.main.item_artist_view_adapter.view.*

class ArtistViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val nameView: TextView = itemView.name_view
    private val itemContainer: View = itemView.item_container

    private var artistListenerAdapter: ArtistListenerAdapter? = null

    fun setListener(artistListenerAdapter: ArtistListenerAdapter?) {
        this.artistListenerAdapter = artistListenerAdapter
    }

    fun bindViews(data: Artist) {
        nameView.text = data.name
        itemContainer.setOnClickListener {
            artistListenerAdapter?.onItemClickListener(data)
        }
    }

    companion object {
        const val layout = R.layout.item_artist_view_adapter
    }
}

interface ArtistListenerAdapter {
    fun onItemClickListener(artist: Artist)
}
