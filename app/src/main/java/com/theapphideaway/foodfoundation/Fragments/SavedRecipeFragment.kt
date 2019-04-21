package com.theapphideaway.foodfoundation.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.theapphideaway.foodfoundation.Adapters.SavedRecipesAdapter
import com.theapphideaway.foodfoundation.AddRecipeActivity
import com.theapphideaway.foodfoundation.Database.DatabaseHelper
import com.theapphideaway.foodfoundation.Models.CustomRecipe

import com.theapphideaway.foodfoundation.R
import kotlinx.android.synthetic.main.content_saved_recipe.*
import kotlinx.android.synthetic.main.content_saved_recipe.view.*
import kotlinx.android.synthetic.main.fragment_saved_recipe.view.*


class SavedRecipeFragment : Fragment() {


    private var savedRecipeAdapter: SavedRecipesAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recipeList: ArrayList<CustomRecipe>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_saved_recipe, container, false)

        var savedRecipeList = rootView.findViewById<RecyclerView>(R.id.saved_recipe_list)
        var emptyView = rootView.findViewById<TextView>(R.id.empty_view)

        recipeList = ArrayList()
        layoutManager = LinearLayoutManager(rootView.context)
        savedRecipeAdapter = SavedRecipesAdapter(recipeList!!, rootView.context)
        rootView.saved_recipe_list.adapter = savedRecipeAdapter
        rootView.saved_recipe_list.layoutManager = layoutManager

        loadQuery("%")
        savedRecipeAdapter!!.notifyDataSetChanged()

        rootView.fab_saved_recipe.setOnClickListener {

            var intent = Intent(rootView.context, AddRecipeActivity::class.java)
            startActivity(intent)

        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadQuery("%")
        savedRecipeAdapter!!.notifyDataSetChanged()
    }

    fun loadQuery(title:String){
        var dbManager= DatabaseHelper(this.context!!)
        val projections= arrayOf("Id","Title","Instructions", "Ingredients", "PictureUrl")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.query(projections,"Title like ?",selectionArgs)
        recipeList!!.clear()
        if(cursor.moveToFirst()){

            do{

                val ID=cursor.getInt(cursor.getColumnIndex("Id"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Instructions=cursor.getString(cursor.getColumnIndex("Instructions"))
                val Ingredients=cursor.getString(cursor.getColumnIndex("Ingredients"))
                val PictureUrl=cursor.getString(cursor.getColumnIndex("PictureUrl"))

                recipeList!!.add(CustomRecipe(ID, Title, Instructions, Ingredients, PictureUrl))

            }while (cursor.moveToNext())
        }
    }
}
