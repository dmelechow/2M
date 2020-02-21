package com.dmelechow.presentation.base

import com.dmelechow.data.model.Album

interface OnClickAlbumItem {
    fun onClickAlbumItemListener(album: Album)
}