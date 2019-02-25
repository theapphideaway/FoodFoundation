package com.theapphideaway.foodfoundation

import android.content.ContentValues
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_recipe_web.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.theapphideaway.foodfoundation.Database.DatabaseHelper
import com.theapphideaway.foodfoundation.Models.RecipeResponse


class RecipeWebActivity : AppCompatActivity() {

    var title: String? = null
    var ingredients: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_recipe_web)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar)

        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);


        web_view.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
                progressbar.visibility = View.GONE
            }
        }

            try {
                var bundle:Bundle=intent.extras
                var url =bundle.getString("Url")
                var mTitle = bundle.getString("title")
                var mIngredients = bundle.getString("ingredients")

                title = mTitle
                ingredients = mIngredients

                web_view.loadUrl(url)
            } catch (ex: Exception) {
                println("Oops")
            }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.web, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        return if (id == R.id.save_button_toolbar) {

            saveRecipe(this, title!!, "", ingredients!!, "")

            true
        } else super.onOptionsItemSelected(item)

    }

    private fun saveRecipe(context: Context, title:String, instructions: String, ingredients: String, picture: String){
        if (title != "" && ingredients != "") {

            var dbManager = DatabaseHelper(this)

            var values = ContentValues()
            values.put("Title", title)
            values.put("Instructions", instructions)
            values.put("Ingredients", ingredients)
            values.put("PictureUrl", picture)

            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(context, "Recipe is Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error: Couldn't Add", Toast.LENGTH_SHORT).show()
            }
        } else{

        }
    }
}
