package com.dmelechow.presentation.albumartist

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmelechow.R
import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtist
import com.dmelechow.data.model.Resource
import com.dmelechow.di.injectModules
import com.dmelechow.presentation.base.viewholder.AlbumListenerAdapter
import kotlinx.android.synthetic.main.search_artist_fragment.*
import org.koin.androidx.viewmodel.ext.viewModel


const val REQUEST_WRITE_EXTERNAL_STORAGE = 10003

const val ARG_ARTIST_MBID = "arg_artist_mbid"

class AlbumArtistFragment : Fragment() {

    private val viewModel: AlbumArtistViewModel by viewModel()

    private lateinit var adapter: AlbumAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var tempAlbum: Album? = null

    private var mbid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_artist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()

        // TODO handle mbid?
        arguments?.getString(ARG_ARTIST_MBID)?.let { it ->
            mbid = it
        }

        viewModel.dataState.observe(viewLifecycleOwner, Observer { renderState(it) })
        initAdapter()
    }

    override fun onStart() {
        super.onStart()
        mbid?.let { viewModel.getAlbums(it) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tempAlbum?.let { viewModel.savedAlbum(it) }
            } else {
                // :TODO permission could get
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private fun renderState(data: Resource<AlbumArtist>) {
        when (data) {
            is Resource.Success -> {
                data.data?.album?.let { albums ->
                    val itemsAdapter = ArrayList<AlbumAdapterData>()
                    for (item in albums) {
                        itemsAdapter.add(AlbumAdapterData(item, VIEW_STATE_ALBUM_ADD))
                    }
                    adapter.notifyItems(itemsAdapter)
                }
            }

            is Resource.Loading -> {
                // :TODO
            }

            is Resource.Error -> {
                // :TODO
            }
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        val permissionCheck = activity?.let { ActivityCompat.checkSelfPermission(it, permission) }
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun initAdapter() {
        adapter = AlbumAdapter(albumListenerAdapter = object : AlbumListenerAdapter {
            override fun onSaveListener(album: Album) {
                if (isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    viewModel.savedAlbum(album)
                } else {
                    tempAlbum = album
                    requestPermission()
                }
            }
        })
        layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
    }

    companion object {
        fun newInstance(mbid: String?): AlbumArtistFragment =
            AlbumArtistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ARTIST_MBID, mbid)
                }
            }
    }
}
