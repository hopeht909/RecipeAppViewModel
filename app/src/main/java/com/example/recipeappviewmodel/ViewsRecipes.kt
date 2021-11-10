package com.example.recipeappviewmodel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappviewmodel.adapter.RVAdapter


class ViewsRecipes : AppCompatActivity() {

    lateinit var rvView : RecyclerView
    lateinit var viewModel: MyViewModel
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views_recipes)

        rvView = findViewById(R.id.rvView)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.getNotes().observe(
            this,
            { recipe ->
                rvAdapter.update(recipe)
            }
        )
        updateRV()
    }

    fun updateRV(){
        rvAdapter = RVAdapter(this)
        rvView.adapter = rvAdapter
        rvView.layoutManager = LinearLayoutManager(this)
    }


    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val mLayout = LinearLayout(this)

        val title = EditText(this)
        title.hint = "Enter the new title"

        val author = EditText(this)
        author.hint = "Enter the new author"

        val ingredients = EditText(this)
        ingredients.hint = "Enter the new ingredients"

        val instruction = EditText(this)
        instruction.hint = "Enter the new instruction"


        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(title)
        mLayout.addView(author)
        mLayout.addView(ingredients)
        mLayout.addView(instruction)

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    _, _ ->
                run{

                    val title = title.text.toString()
                    val author = author.text.toString()
                    val ingredients = ingredients.text.toString()
                    val instruction = instruction.text.toString()

                    viewModel.updateRecipe(id,title,author,ingredients,instruction )
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(mLayout)
        alert.show()
    }
    fun checkDeleteDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val checkTextView = TextView(this)
        checkTextView.text = "  Are sure you want to delete this note ?!"

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {

                    _, _ ->
                run{
                    viewModel.deleteRecipe(id)
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Check the deletion")
        alert.setView(checkTextView)
        alert.show()
    }
}