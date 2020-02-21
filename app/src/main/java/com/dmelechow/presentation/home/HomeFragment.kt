package com.dmelechow.presentation.home

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
import com.dmelechow.data.model.Resource
import com.dmelechow.di.injectModules
import com.dmelechow.presentation.MainActivity
import com.dmelechow.presentation.albumartist.AlbumAdapter
import com.dmelechow.presentation.albumartist.AlbumAdapterData
import com.dmelechow.presentation.albumartist.VIEW_STATE_ALBUM_REMOVE
import com.dmelechow.presentation.base.viewholder.AlbumRemoveListenerAdapter
import com.dmelechow.presentation.searh.SearchArtistFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.search_artist_fragment.recycler_view
import org.koin.androidx.viewmodel.ext.viewModel

const val REQUEST_WRITE_EXTERNAL_STORAGE = 10003


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: AlbumAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()

        searchView.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity)?.attachFragment(SearchArtistFragment.newInstance())
            }
        }
        viewModel.dataState.observe(viewLifecycleOwner, Observer { renderState(it) })
        initAdapter()
        if (isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermission()
        }
        viewModel.getSavedAlbums()
    }

    private fun renderState(data: Resource<ArrayList<Album>>) {
        when (data) {
            is Resource.Success -> {
                data.data?.let { albums ->

                    val itemsAdapter = ArrayList<AlbumAdapterData>()
                    for (item in albums) {
                        itemsAdapter.add(AlbumAdapterData(item, VIEW_STATE_ALBUM_REMOVE))
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.getSavedAlbums()
            } else {
                // :TODO permission could get
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private fun initAdapter() {
        adapter = AlbumAdapter(albumRemoveListenerAdapter = object : AlbumRemoveListenerAdapter {
            override fun onRemoveListener(album: Album) {
                viewModel.removeSavedAlbum(album)
                adapter.remove(album)
            }
        })
        layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
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

    companion object {
        fun newInstance() = HomeFragment()
    }

}
