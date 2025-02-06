package com.example.soopimageloader.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soopimageloader.R
import com.example.soopimageloader.databinding.ListItemThumbnailBinding
import com.example.soopimageloader.utils.ImageLoader
import com.example.soopimageloader.utils.formatWithCommas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CategoryAdapter(
    private val imageLoader: ImageLoader,
    private val lifecycleScope: CoroutineScope
) : PagingDataAdapter<CategoryItem, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ListItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CategoryViewHolder(private val binding: ListItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryItem) {

            binding.tvTitle.text = category.categoryName
            binding.liveCount.tvCount.text = formatWithCommas(category.viewCnt)

            binding.fbTags.removeAllViews()
            for (tag in category.fixedTags) {
                val tagView = LayoutInflater.from(binding.fbTags.context)
                    .inflate(R.layout.list_item_category, binding.fbTags, false) as TextView

                tagView.text = tag
                binding.fbTags.addView(tagView)
            }

            val imagePath = category.cateImg

            lifecycleScope.launch {
                if (imagePath.startsWith("http")) {
                    imageLoader.loadImage(imagePath, binding.ivThumbnail)
                } else {
                    imageLoader.loadImageFromFile(imagePath, binding.ivThumbnail)
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