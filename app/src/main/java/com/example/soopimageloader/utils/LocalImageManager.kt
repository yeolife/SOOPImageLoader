package com.example.soopimageloader.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalImageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val cacheDir = context.cacheDir

    suspend fun downloadImageToLocal(imageUrl: String, categoryNo: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(cacheDir, "cate_$categoryNo.webp")

                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .submit()
                    .get()

                FileOutputStream(file).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 80, out)
                }

                file.absolutePath
            } catch (e: Exception) {
                imageUrl
            }
        }
    }

    fun cleanupCache(maxAgeMillis: Long = 7 * 24 * 60 * 60 * 1000L) {
        val currentTime = System.currentTimeMillis()
        cacheDir.listFiles()?.forEach { file ->
            if (currentTime - file.lastModified() > maxAgeMillis) {
                file.delete()
            }
        }
    }
}