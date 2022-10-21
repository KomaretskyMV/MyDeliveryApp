package com.kmv.mydeliveryapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmv.mydeliveryapp.data.MenuRepository
import com.kmv.mydeliveryapp.domain.GetMenuUseCase
import com.kmv.mydeliveryapp.entity.data_classes.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel(){
    private val repository = MenuRepository()
    private val useCase = GetMenuUseCase(repository)

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val _pizza = MutableStateFlow<List<Hit>>(emptyList())
    val pizza = _pizza.asStateFlow()

    val _salads = MutableStateFlow<List<Hit>>(emptyList())
    val salads = _salads.asStateFlow()

    val _chicken = MutableStateFlow<List<Hit>>(emptyList())
    val chicken = _chicken.asStateFlow()

    val _snacks = MutableStateFlow<List<Hit>>(emptyList())
    val snacks = _snacks.asStateFlow()

    val _desserts = MutableStateFlow<List<Hit>>(emptyList())
    val desserts = _desserts.asStateFlow()

    val _drinks = MutableStateFlow<List<Hit>>(emptyList())
    val drinks = _drinks.asStateFlow()

    fun loadDishes(dishType: String, _flow: MutableStateFlow<List<Hit>>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                useCase.execute(dishType, "1-5")
            }.fold(
                onSuccess = {
                    _flow.value = it
                },
                onFailure = {
                    Log.d("MenuViewModel", it.message ?: "")
                }
            )
            _isLoading.value = false
        }
    }

}