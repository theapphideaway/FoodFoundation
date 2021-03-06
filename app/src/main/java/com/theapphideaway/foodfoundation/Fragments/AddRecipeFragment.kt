package com.theapphideaway.foodfoundation.Fragments


import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.theapphideaway.foodfoundation.Database.DatabaseHelper

import com.theapphideaway.foodfoundation.R
import kotlinx.android.synthetic.main.content_add_recipe.*
import kotlinx.android.synthetic.main.fragment_add_recipe_fragment.view.*
import kotlinx.android.synthetic.main.fragment_saved_recipe.view.*

class AddRecipeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_add_recipe_fragment, container, false)



        rootView.fab.setOnClickListener { saveRecipe(rootView.context, title_edit_text.text.toString(),
            instructions_edit_text.text.toString(), ingredients_edit_text.text.toString(), "Placeholder_Text") }

        return rootView
    }

    private fun saveRecipe(context: Context, title:String, instructions: String, ingredients: String, picture: String){
        if (title != "" && ingredients != "") {

            var dbManager = DatabaseHelper(context)

            var values = ContentValues()
            values.put("Title", title)
            values.put("Instructions", instructions)
            values.put("Ingredients", ingredients)
            values.put("PictureUrl", picture)

            if (id == 0) {
                val ID = dbManager.Insert(values)
                if (ID > 0) {
                    Toast.makeText(context, "Recipe is Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error: Couldn't Add", Toast.LENGTH_SHORT).show()
                }
            } else {
                var selectionArgs = arrayOf(id.toString())
                val Id = dbManager.update(values, "Id=?", selectionArgs)
                if (Id > 0) {
                    Toast.makeText(context, "Recipe is Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error: Couldn't Update", Toast.LENGTH_SHORT).show()
                }
            }


        } else{

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Warning")

            builder.setMessage("Title and ingredients need to have values or this recipe will be deleted. " +
                    "Are you sure you want to delete this recipe?")

            builder.setPositiveButton("Yes"){dialog, which ->
                Toast.makeText(context, "Didn't Save", Toast.LENGTH_LONG).show()
                true
            }

            builder.setNegativeButton("No"){dialog,which ->
                Toast.makeText(context,"No changes made", Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }
    }


}
