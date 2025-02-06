package com.example.soopimageloader.utils

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

interface ImageLoader {
    suspend fun loadImage(url: String, imageView: ImageView)
    suspend fun loadImageFromFile(filePath: String, imageView: ImageView)
    suspend fun loadImageAsBitmap(url: String): Bitmap?
}

@Singleton
class ImageLoaderImpl @Inject constructor(): ImageLoader {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, bitmap: Bitmap): Int {
            return bitmap.byteCount / 1024
        }
    }

    override suspend fun loadImage(url: String, imageView: ImageView) {
        try {
            var bitmap = getBitmapFromMemCache(url)

            if (bitmap == null) {
                bitmap = withContext(Dispatchers.IO) {
                    try {
                        val connection = URL(url).openConnection() as HttpURLConnection
                        connection.apply {
                            connectTimeout = 5000
                            readTimeout = 5000
                            doInput = true
                        }

                        connection.inputStream.use { input ->
                            BitmapFactory.decodeStream(input)?.also { decodedBitmap ->
                                addBitmapToMemoryCache(url, decodedBitmap)
                            }
                        }
                    } catch (e: Exception) {
                        e.stackTraceToString()
                        null
                    }
                }
            }

            withContext(Dispatchers.Main) {
                bitmap?.let {
                    imageView.setImageBitmap(it)
                }
            }
        } catch (e: Exception) {
            e.stackTraceToString()
        }
    }

    override suspend fun loadImageFromFile(filePath: String, imageView: ImageView) {
        try {
            var bitmap = getBitmapFromMemCache(filePath)

            if (bitmap == null) {
                bitmap = withContext(Dispatchers.IO) {
                    BitmapFactory.decodeFile(filePath)?.also { decodedBitmap ->
                        addBitmapToMemoryCache(filePath, decodedBitmap)
                    }
                }
            }

            withContext(Dispatchers.Main) {
                bitmap?.let {
                    imageView.setImageBitmap(it)
                }
            }
        } catch (e: Exception) {
            e.stackTraceToString()
        }
    }

    override suspend fun loadImageAsBitmap(url: String): Bitmap? {
        return try {
            var bitmap = getBitmapFromMemCache(url)

            if (bitmap == null) {
                withContext(Dispatchers.IO) {
                    val connection = URL(url).openConnection() as HttpURLConnection
                    connection.apply {
                        connectTimeout = 5000
                        readTimeout = 5000
                        doInput = true
                    }

                    bitmap = connection.inputStream.use { input ->
                        BitmapFactory.decodeStream(input)
                    }

                    bitmap?.let { addBitmapToMemoryCache(url, it) }
                }
            }

            bitmap
        } catch (e: Exception) {
            e.stackTraceToString()
            null
        }
    }

    private fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap)
        }
    }

    private fun getBitmapFromMemCache(key: String): Bitmap? {
        return memoryCache.get(key)
    }
}