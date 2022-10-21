package com.kmv.mydeliveryapp.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MenuPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment = MenuFragment.newInstance(position)
}