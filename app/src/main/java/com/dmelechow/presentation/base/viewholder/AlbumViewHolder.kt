package com.dmelechow.presentation.base.viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.dmelechow.R
import com.dmelechow.data.model.Album
import com.dmelechow.utils.ImageLoader
import kotlinx.android.synthetic.main.item_album_remove_view_adapter.view.*

class AlbumViewHolder constructor(itemView: View) : ViewHolder<Album>(itemView) {

    private val nameView: TextView = itemView.name_album_view
    private val imageAlbumView: AppCompatImageView = itemView.image_album_view
    private val saveButton: Button = itemView.remove_button

    private var albumListenerAdapter: AlbumListenerAdapter? = null

    fun setListener(albumListenerAdapter: AlbumListenerAdapter?){
        this.albumListenerAdapter = albumListenerAdapter
    }

    override fun bindViews(data: Album) {
        nameView.text = data.name
        if (data.image != null && data.image!!.isNotEmpty()) {
            ImageLoader.load(itemView.context, data.image!![data.image!!.size - 1].text, imageAlbumView)
        }
        saveButton.setOnClickListener {
            albumListenerAdapter?.onSaveListener(data)
        }
    }

    companion object {
        const val layout = R.layout.item_album_view_adapter
    }

}

interface AlbumListenerAdapter {
    fun onSaveListener(album: Album)
}

