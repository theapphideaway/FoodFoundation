package com.theapphideaway.foodfoundation.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.theapphideaway.foodfoundation.AddRecipeActivity
import com.theapphideaway.foodfoundation.Database.DatabaseHelper
import com.theapphideaway.foodfoundation.Models.CustomRecipe
import com.theapphideaway.foodfoundation.Models.RecipeResponse
import com.theapphideaway.foodfoundation.R
import com.theapphideaway.foodfoundation.RecipeWebActivity
import kotlinx.android.synthetic.main.fragment_result.view.*

class SavedRecipesAdapter(
    private val mCustomRecipe: ArrayList<CustomRecipe>,
    private val context: Context
) : RecyclerView.Adapter<SavedRecipesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: SavedRecipesAdapter.ViewHolder, position: Int) {
        val item = mCustomRecipe[position]
        holder.mTitle.text = item.title

        holder.itemView.setOnClickListener { goToRecipe(item) }

        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Delete Recipe")

            builder.setMessage("Are you sure you want to delete this recipe?")

            builder.setPositiveButton("YES"){dialog, which ->

                var dbManager = DatabaseHelper(this.context!!)

                if(mCustomRecipe.size == 1){
                    dbManager.deleteAll()
                    mCustomRecipe.removeAll(mCustomRecipe)
                    notifyItemRemoved(position)
                }
                val selectionArgs= arrayOf(item.id.toString())
                dbManager.delete("Id=?", selectionArgs )
                mCustomRecipe.removeAt(position)
                notifyItemRemoved(position)
                true

            }

            builder.setNegativeButton("No"){dialog,which ->
                Toast.makeText(context,"No changes made",Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()

            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mCustomRecipe.size

    fun goToRecipe(recipe: CustomRecipe){
        var intent = Intent(context, AddRecipeActivity::class.java)
        intent.putExtra("Id", recipe.id)
        intent.putExtra("Title", recipe.title)
        intent.putExtra("Instructions", recipe.instructions)
        intent.putExtra("Ingredients", recipe.ingredients)
        intent.putExtra("PictureUrl", recipe.pictureUrl)
        ContextCompat.startActivity(context, intent, null)
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.title
    }
}