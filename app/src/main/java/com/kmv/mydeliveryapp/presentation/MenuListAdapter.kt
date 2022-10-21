package com.kmv.mydeliveryapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmv.mydeliveryapp.databinding.MenuItemBinding
import com.kmv.mydeliveryapp.entity.data_classes.Hit

class MenuListAdapter : ListAdapter<Hit, MenuViewHolder>(DiffUtilCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            label.text = item.recipe.label
            ingredients.text = item.recipe.ingredientLines.toString()
             item.let {
                 Glide
                     .with(image.context)
                     .load(it.recipe.image)
                     .into(image)
             }
        }
    }

}

class MenuViewHolder(val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean =
        oldItem.recipe.label == newItem.recipe.label

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean =
        oldItem == newItem
}