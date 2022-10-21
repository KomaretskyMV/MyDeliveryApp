package com.kmv.mydeliveryapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kmv.mydeliveryapp.databinding.FragmentMenuBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val menuViewModel: MenuViewModel by viewModels()
    private val menuListAdapter = MenuListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = menuViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.menuRecycler.adapter = menuListAdapter

        val position = arguments?.getInt(POSITION_ARG)

        when(position) {
            0 -> {
                menuViewModel.loadDishes("Pizza", menuViewModel._pizza)
                menuViewModel.pizza.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            1 -> {
                menuViewModel.loadDishes("Salad", menuViewModel._salads)
                menuViewModel.salads.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            2 -> {
                menuViewModel.loadDishes("Chicken", menuViewModel._chicken)
                menuViewModel.chicken.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            3 -> {
                menuViewModel.loadDishes("Snack", menuViewModel._snacks)
                menuViewModel.snacks.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            4 -> {
                menuViewModel.loadDishes("Dessert", menuViewModel._desserts)
                menuViewModel.desserts.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            5 -> {
                menuViewModel.loadDishes("Drink", menuViewModel._drinks)
                menuViewModel.drinks.onEach {
                    menuListAdapter.submitList(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        var POSITION_ARG = "position_arg"
        @JvmStatic
        fun newInstance(position: Int) = MenuFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }
}