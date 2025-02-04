package com.example.soopimageloader.ui.Category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soopimageloader.R
import com.example.soopimageloader.databinding.ListItemThumbnailBinding
import com.example.soopimageloader.utils.formatWithCommas

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(private val binding: ListItemThumbnailBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
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

                Glide.with(ivThumbnail.context)
                    .load(category.cateImg)
                    .into(binding.ivThumbnail)
            }
        }
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.categoryNo == newItem.categoryNo
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }
}