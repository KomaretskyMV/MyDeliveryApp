package com.kmv.mydeliveryapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmv.mydeliveryapp.R
import com.kmv.mydeliveryapp.databinding.BannerItemBinding

class BannerAdapter : RecyclerView.Adapter<BannerViewHolder>() {

    private val images = listOf(
        R.drawable.fast_food,
        R.drawable.chicken_and_ribs,
        R.drawable.fish_steak,
        R.drawable.pasta,
        R.drawable.seafood
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            BannerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = images[position]
        Glide
            .with(holder.binding.image.context)
            .load(item)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = images.size
}

class BannerViewHolder(val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root)
