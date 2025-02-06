package com.example.soopimageloader.utils

import android.content.Context
import android.graphics.Bitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalImageManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageLoader: ImageLoader
) {
    private val cacheDir = context.cacheDir

    suspend fun downloadImageToLocal(imageUrl: String, categoryNo: String): String {
        return withContext(Dispatchers.IO) {
            try {
                if(!cacheDir.exists()) {
                    cacheDir.mkdirs()
                }

                val file = File(cacheDir, "cate_$categoryNo.webp")

                val bitmap = imageLoader.loadImageAsBitmap(imageUrl)

                FileOutputStream(file).use { out ->
                    bitmap?.compress(Bitmap.CompressFormat.WEBP, 80, out)
                }

                file.absolutePath
            } catch (e: Exception) {
                imageUrl
            }
        }
    }

    fun cleanupDiskCache(expiredPaths: List<String>) {
        expiredPaths.forEach { path ->
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        }
    }
}