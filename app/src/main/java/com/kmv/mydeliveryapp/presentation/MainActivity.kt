package com.kmv.mydeliveryapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kmv.mydeliveryapp.databinding.ActivityMainBinding

private val TAB_TITLES = arrayOf(
    "Pizza",
    "Salads",
    "Chicken"
)

private val cities = arrayOf(
    "Moscow",
    "St.Petersburg",
    "Novosibirsk",
    "Ekaterinburg",
    "Kazan",
    "N.Novgorod",
    "Chelyabinsk",
    "Samara"
)

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val bannerAdapter = BannerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuViewPager = binding.menuViewpager
        menuViewPager.adapter = MenuPagerAdapter(this)

        val tabs = binding.tabs
        TabLayoutMediator(tabs, menuViewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

        binding.bannerRecycler.adapter = bannerAdapter

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.citiesListSpinner.adapter = spinnerAdapter

    }
}