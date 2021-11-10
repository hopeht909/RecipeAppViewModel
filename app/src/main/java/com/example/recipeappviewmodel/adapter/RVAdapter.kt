package com.example.recipeappviewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappviewmodel.ViewsRecipes
import com.example.recipeappviewmodel.database.RecipeEntity
import com.example.recipeappviewmodel.databinding.ItemRowBinding


class RVAdapter(private val activity: ViewsRecipes) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    private var notes = emptyList<RecipeEntity>()

    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = notes[position]

        holder.binding.apply {
            tvNote.text =
                "Title: ${item.title}\n" +
                        "Auther: ${item.author}\n" +
                        "Ingredients: ${item.ingredients}\n" +
                        "Instruction: ${item.instruction}\n\n"

            ibEditNote.setOnClickListener {
                activity.raiseDialog(item.id)

            }
            ibDeleteNote.setOnClickListener {
                activity.checkDeleteDialog(item.id)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes: List<RecipeEntity>){
        println("UPDATING DATA")
        this.notes = notes
        notifyDataSetChanged()
    }
}