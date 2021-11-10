package com.example.recipeappviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipeappviewmodel.database.RecipeDatabase
import com.example.recipeappviewmodel.database.RecipeEntity
import com.example.recipeappviewmodel.database.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RecipeRepository
    private val notes: LiveData<List<RecipeEntity>>

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        notes = repository.getNotes
    }

    fun getNotes(): LiveData<List<RecipeEntity>>{
        return notes
    }

    fun addRecipe(title: String, author: String, ingredients: String, instruction: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addRecipe(RecipeEntity(0, title,author,ingredients, instruction))
        }
    }

    fun updateRecipe(ID : Int, title: String, author: String, ingredients: String, instruction: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateRecipe(RecipeEntity(ID, title,author,ingredients,instruction))
        }
    }

    fun deleteRecipe(noteID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteRecipe(RecipeEntity(noteID,"","","",""))
        }
    }
}