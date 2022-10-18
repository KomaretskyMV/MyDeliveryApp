package com.kmv.mydeliveryapp.domain

import com.kmv.mydeliveryapp.data.MenuRepository
import com.kmv.mydeliveryapp.entity.data_classes.Hit
import com.kmv.mydeliveryapp.entity.data_classes.Recipe

class GetMenuUseCase(private val repository: MenuRepository) {
    suspend fun execute(dishType: String, ingredientsNumber: String): List<Hit> {
        return repository.getMenu(dishType, ingredientsNumber)
    }
}