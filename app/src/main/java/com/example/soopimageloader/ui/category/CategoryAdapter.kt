package com.example.soopimageloader.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.soopimageloader.R
import com.example.soopimageloader.databinding.ListItemThumbnailBinding
import com.example.soopimageloader.utils.formatWithCommas
import java.io.File

class CategoryAdapter: PagingDataAdapter<CategoryItem, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class CategoryViewHolder(private val binding: ListItemThumbnailBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryItem) {
            with(binding) {
                tvTitle.text = category.categoryName
                liveCount.tvCount.text = formatWithCommas(category.viewCnt)

                fbTags.removeAllViews()
                for(tag in category.fixedTags) {
                    val tagView = LayoutInflater.from(fbTags.context)
                        .inflate(R.layout.list_item_category, fbTags, false) as TextView

                    tagView.text = tag
                    fbTags.addView(tagView)
                }

                val imagePath = category.cateImg
                val glideRequest = Glide.with(itemView.context)

                if (imagePath.startsWith("http")) { // 원격
                    glideRequest.load(imagePath)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(binding.ivThumbnail)
                } else { // 로컬
                    glideRequest.load(File(imagePath))
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.ivThumbnail)
                }
            }
        }
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CategoryItem>() {
            override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem.categoryNo == newItem.categoryNo
            }

            override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}