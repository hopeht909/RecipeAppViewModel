package com.example.recipeappviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.recipeappviewmodel.adapter.RVAdapter


class MainActivity : AppCompatActivity() {

    lateinit var btSave : Button
    lateinit var etTitle : EditText
    lateinit var etAuthor : EditText
    lateinit var etIngredients : EditText
    lateinit var etInstruction : EditText
    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSave = findViewById(R.id.btSave)

        etTitle = findViewById(R.id.etTitle)
        etAuthor = findViewById(R.id.etAuthor)
        etIngredients = findViewById(R.id.etIngredients)
        etInstruction = findViewById(R.id.etInstruction)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        btSave.setOnClickListener {
            if(etTitle.text.isNotBlank() &&etAuthor.text.isNotBlank()
                && etIngredients.text.isNotBlank()&& etInstruction.text.isNotBlank()){

                val title = etTitle.text.toString()
                val author = etAuthor.text.toString()
                val ingredients = etIngredients.text.toString()
                val instruction = etInstruction.text.toString()

                viewModel.addRecipe(title,author,ingredients, instruction)
                etTitle.text.clear()
                etTitle.clearFocus()

                etAuthor.text.clear()
                etAuthor.clearFocus()

                etIngredients.text.clear()
                etIngredients.clearFocus()

                etInstruction.text.clear()
                etInstruction.clearFocus()


                Toast.makeText(this, "Recipe Added", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "please add a Recipe", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun viewReceipe(view: android.view.View) {
        val intent = Intent(this, ViewsRecipes::class.java)
        startActivity(intent)
    }


}