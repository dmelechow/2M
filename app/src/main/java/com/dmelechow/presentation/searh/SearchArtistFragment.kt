package com.dmelechow.presentation.searh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmelechow.R
import com.dmelechow.data.model.Artist
import com.dmelechow.data.model.Resource
import com.dmelechow.data.model.SearchResult
import com.dmelechow.di.injectModules
import com.dmelechow.presentation.MainActivity
import com.dmelechow.presentation.albumartist.AlbumArtistFragment
import com.dmelechow.presentation.base.viewholder.ArtistListenerAdapter
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.search_artist_fragment.*
import org.koin.androidx.viewmodel.ext.viewModel
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SearchArtistFragment : Fragment() {

    private val viewModel: SearchArtistViewModel by viewModel()

    private lateinit var adapter: SearchArtistAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var searchSubscription: Subscription

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_artist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()
        viewModel.searchState.observe(viewLifecycleOwner, Observer { searchState(it) })
        initAdapter()

        searchSubscription = RxTextView.textChanges(search_view)
            .skip(1)
            .debounce(300, TimeUnit.MICROSECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toString() }
            .subscribe { text ->
                viewModel.search(text)
            }
    }

    override fun onDestroyView() {
        if (searchSubscription.isUnsubscribed) searchSubscription.unsubscribe()
        super.onDestroyView()
    }

    private fun initAdapter() {
        adapter = SearchArtistAdapter(object : ArtistListenerAdapter{
            override fun onItemClickListener(artist: Artist) {
                if (activity is MainActivity) {
                    (activity as MainActivity)?.attachFragment(AlbumArtistFragment.newInstance(artist.mbid))
                }
            }
        })
        layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
    }

    private fun searchState(data: Resource<SearchResult>) {
        when (data) {
            is Resource.Success -> {
                data.data?.artistmatches?.artist?.let { adapter.notifyItems(it) }
            }

            is Resource.Loading -> {
                // :TODO
            }

            is Resource.Error -> {
                // :TODO
            }
        }
    }

    companion object {
        fun newInstance() = SearchArtistFragment()
    }
}
