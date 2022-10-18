package com.kmv.mydeliveryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmv.mydeliveryapp.data.MenuRepository
import com.kmv.mydeliveryapp.domain.GetMenuUseCase
import com.kmv.mydeliveryapp.entity.data_classes.Hit
import com.kmv.mydeliveryapp.entity.data_classes.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel(){
    private val repository = MenuRepository()
    private val useCase = GetMenuUseCase(repository)

    private val _pizza = MutableStateFlow<List<Hit>>(emptyList())
    val pizza = _pizza.asStateFlow()

    private val _salads = MutableStateFlow<List<Hit>>(emptyList())
    val salads = _salads.asStateFlow()

    private val _chicken = MutableStateFlow<List<Hit>>(emptyList())
    val chicken = _chicken.asStateFlow()

    init {
        loadDishes("Pizza", _pizza)
        loadDishes("Salad", _salads)
        loadDishes("Chicken", _chicken)
    }

    private fun loadDishes(dishType: String, flow: MutableStateFlow<List<Hit>>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                flow.value = useCase.execute(dishType, "1-5")
            }
        }
    }

}