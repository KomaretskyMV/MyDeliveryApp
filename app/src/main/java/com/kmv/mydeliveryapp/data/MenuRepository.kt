package com.kmv.mydeliveryapp.data

import com.kmv.mydeliveryapp.entity.RecipesList
import com.kmv.mydeliveryapp.entity.data_classes.Hit
import com.kmv.mydeliveryapp.entity.data_classes.Recipe
import com.kmv.mydeliveryapp.entity.data_classes.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MenuRepository {
    suspend fun getMenu(dishType: String, ingredientsNumber: String) : List<Hit> {
        return RetrofitServices.menuApi.getMenu(
            APP_KEY,
            APP_ID,
            TYPE[0],
            dishType,
            ingredientsNumber
        ).hits
    }
}

private const val BASE_URL = "https://api.edamam.com"
private const val APP_KEY = "6f82304ba6c830621d17b52ee9d15725"
private const val APP_ID = "e530da10"
private val TYPE = arrayOf("public", "user", "any")

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val menuApi: MenuApi = retrofit.create(
        MenuApi::class.java
    )
}

interface MenuApi {
    @GET("/api/recipes/v2")
    suspend fun getMenu(
        @Query("app_key") appKey: String,
        @Query("app_id") appId: String,
        @Query("type") type: String,
        @Query("q") dishType: String,
        @Query("ingr") ingredientsNumber: String
    ) : Response
}