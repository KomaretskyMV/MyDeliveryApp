package com.kmv.mydeliveryapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.kmv.mydeliveryapp.R
import com.kmv.mydeliveryapp.databinding.FragmentHomeBinding

private val TAB_TITLES = arrayOf(
    "Pizza",
    "Salads",
    "Chicken",
    "Snacks",
    "Desserts",
    "Drinks"
)

private val cities = arrayOf(
    "Moscow",
    "St.Petersburg",
    "Novosibirsk",
    "Yekaterinburg",
    "Kazan",
    "N.Novgorod",
    "Chelyabinsk",
    "Samara"
)

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val bannerAdapter = BannerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuViewPager = binding.menuViewpager
        menuViewPager.adapter = MenuPagerAdapter(requireActivity() as AppCompatActivity)

        val tabs = binding.tabs
        TabLayoutMediator(tabs, menuViewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

        binding.bannerRecycler.adapter = bannerAdapter

        val spinnerAdapter = ArrayAdapter(requireActivity(), R.layout.cities_spinner_item, cities)
        spinnerAdapter.setDropDownViewResource(R.layout.cities_spinner_dropdown_item)
        binding.citiesListSpinner.adapter = spinnerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}