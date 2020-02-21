package com.dmelechow.presentation.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmelechow.R
import com.dmelechow.data.model.Album
import com.dmelechow.di.injectModules
import com.dmelechow.utils.ImageLoader
import kotlinx.android.synthetic.main.artist_fragment.*
import kotlinx.android.synthetic.main.item_artist_view_adapter.*

const val ARG_ARTIST = "arg_artist"

class ArtistFragment : Fragment() {

    private var album: Album? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()

        arguments?.getSerializable(ARG_ARTIST)?.let { it ->
            album = (it as Album)
        }

        name_album_view.text = album?.name
        if (album?.image != null && album?.image!!.isNotEmpty()) {
            this.context?.let { ImageLoader.load(it, album?.image!![album?.image!!.size - 1].text, image_album_view) }
        }

        album?.artist?.let {
            tracks_album_view.text = "Author: ${it.name}"
        }
    }

    companion object {
        fun newInstance(album: Album): ArtistFragment =
            ArtistFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ARTIST, album)
                }
            }
    }
}
