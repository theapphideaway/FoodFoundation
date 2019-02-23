package com.theapphideaway.foodfoundation

import android.content.ContentValues
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.theapphideaway.foodfoundation.Database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_add_recipe.*
import kotlinx.android.synthetic.main.content_add_recipe.*
import kotlinx.android.synthetic.main.fragment_add_recipe_fragment.view.*

class AddRecipeActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        try{
            var bundle:Bundle=intent.extras
            id=bundle.getInt("Id",0)
            title_edit_text.setText(bundle.getString("Title") )
            instructions_edit_text.setText(bundle.getString("Instructions") )
            ingredients_edit_text.setText(bundle.getString("Ingredients"))
        }catch (ex:Exception){}

        fab.setOnClickListener { saveRecipe(this, title_edit_text.text.toString(),
            instructions_edit_text.text.toString(), ingredients_edit_text.text.toString(), "Placeholder_Text") }
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

            onBackPressed()

        } else{

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Warning")

            builder.setMessage("Title and ingredients need to have values or this recipe will be deleted. " +
                    "Are you sure you want to delete this recipe?")

            builder.setPositiveButton("Yes"){dialog, which ->
                Toast.makeText(context, "Didn't Save", Toast.LENGTH_LONG).show()
                onBackPressed()
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
