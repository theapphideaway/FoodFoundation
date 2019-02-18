package com.theapphideaway.foodfoundation.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.foodfoundation.Models.RecipeResponse

import com.theapphideaway.foodfoundation.R
import com.theapphideaway.foodfoundation.Services.RecipeService
import kotlinx.android.synthetic.main.fragment_search_recipe.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchRecipeFragment : Fragment() {

    val recipeService = RecipeService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search_recipe, container, false)

        rootView.url_search_edit_text.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                GlobalScope.launch(Dispatchers.Main) {
                    var headline = recipeService.getRecipeApi().getSearchedRecipes(
                        rootView.url_search_edit_text.text.toString()
                    ).await()

                    switchToResults(headline, rootView.url_search_edit_text.text.toString())
                }
            }
            false
        })

        return rootView
    }

    private fun switchToResults(recipe: RecipeResponse, searchText: String) {
        val manager = activity!!.supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        var transaction = manager.beginTransaction()
        var searchResults = ResultFragment()

        val bundle = Bundle()
        bundle.putSerializable("results", recipe)
        bundle.putString("SearchText", searchText)
        searchResults.arguments = bundle

        transaction.attach(ResultFragment())
        transaction.replace(R.id.fragment, searchResults).commit()

    }
}
