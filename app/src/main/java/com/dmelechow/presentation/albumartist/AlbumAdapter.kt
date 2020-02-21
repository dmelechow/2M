package com.dmelechow.presentation.albumartist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmelechow.data.model.Album
import com.dmelechow.presentation.base.viewholder.*


const val VIEW_STATE_ALBUM_ADD = 1
const val VIEW_STATE_ALBUM_REMOVE = 2

class AlbumAdapter(
    private val albumListenerAdapter: AlbumListenerAdapter? = null,
    private val albumRemoveListenerAdapter: AlbumRemoveListenerAdapter? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<AlbumAdapterData> = ArrayList()

    fun notifyItems(items: List<AlbumAdapterData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun remove(album: Album) {
        val sortedItems = items.filter {
            return@filter it.data.mbid != album.mbid
        }
        items.clear()
        items.addAll(sortedItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_STATE_ALBUM_ADD -> {
                AlbumViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        AlbumViewHolder.layout,
                        parent,
                        false
                    )
                )
            }

            else -> {
                AlbumRemoveViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        AlbumRemoveViewHolder.layout,
                        parent,
                        false
                    )
                )
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumRemoveViewHolder) {
            holder.setListener(albumRemoveListenerAdapter)
            holder.bindViews(items[position].data)
        } else if (holder is AlbumViewHolder) {
            holder.setListener(albumListenerAdapter)
            holder.bindViews(items[position].data)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }
}


class AlbumAdapterData(
    var data: Album,
    var viewType: Int = 0
)

