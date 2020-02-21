package com.dmelechow.presentation.base.viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.dmelechow.R
import com.dmelechow.data.model.Album
import com.dmelechow.presentation.base.OnClickAlbumItem
import com.dmelechow.utils.ImageLoader
import kotlinx.android.synthetic.main.item_album_remove_view_adapter.view.*

class AlbumRemoveViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView: TextView = itemView.name_album_view
    private val imageAlbumView: AppCompatImageView = itemView.image_album_view
    private val removeButton: Button = itemView.remove_button
    private val container: View = itemView.container

    private var albumListenerAdapter: AlbumRemoveListenerAdapter? = null

    fun setListener(albumListenerAdapter: AlbumRemoveListenerAdapter?){
        this.albumListenerAdapter = albumListenerAdapter
    }

    fun bindViews(data: Album) {
        nameView.text = data.name
        if (data.image != null && data.image!!.isNotEmpty()) {
            ImageLoader.load(itemView.context, data.image!![data.image!!.size - 1].text, imageAlbumView)
        }

        container.setOnClickListener {
            albumListenerAdapter?.onClickAlbumItemListener(data)
        }

        removeButton.setOnClickListener {
            albumListenerAdapter?.onRemoveListener(data)
        }
    }

    companion object {
        const val layout = R.layout.item_album_remove_view_adapter
    }

}

interface AlbumRemoveListenerAdapter: OnClickAlbumItem {
    fun onRemoveListener(album: Album)
}



