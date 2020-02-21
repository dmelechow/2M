package com.dmelechow.data.datasource

import android.content.Context
import android.os.Environment
import com.dmelechow.data.model.Album
import io.reactivex.Completable
import io.reactivex.Observable
import java.io.*


class FileDataSource(val context: Context) {

    fun saveAlbumToFile(album: Album): Completable {
        return try {
            val items = getAlbums()
            items.add(album)
            return saveAlbumsFile(items)
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    fun getAlbumsToFile(): Observable<ArrayList<Album>> {
        return try {
            val fis = FileInputStream(getFile())
            val ois = ObjectInputStream(fis)
            val albums: ArrayList<Album> = ois.readObject() as ArrayList<Album>
            ois.close()
            Observable.just(albums)
        } catch (e: Exception) {
            Observable.error(e)
        }
    }

    fun removeSavedAlbum(album: Album): Completable {
        return try {
            val items = getAlbums()
            val sortedItems = items.filter {
                return@filter it.mbid != album.mbid
            }
            return saveAlbumsFile(sortedItems as ArrayList<Album>)
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    private fun getAlbums(): ArrayList<Album> {
        return try {
            val fis = FileInputStream(getFile())
            val ois = ObjectInputStream(fis)
            val albums = ois.readObject() as ArrayList<Album>
            ois.close()
            albums
        } catch (e: Exception) {
            ArrayList()
        }
    }

    private fun getFile() = File(
        Environment.getExternalStorageDirectory(),
        "lastfmalbums.tmp"
    )

    private fun saveAlbumsFile(albums: ArrayList<Album>): Completable {
        return try {
            val fos = FileOutputStream(getFile())
            val oos = ObjectOutputStream(fos)
            oos.writeObject(albums)
            oos.close()
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }
}

