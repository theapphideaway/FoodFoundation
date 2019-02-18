package com.theapphideaway.foodfoundation.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.foodfoundation.Models.RecipeResponse
import com.theapphideaway.foodfoundation.R

import com.theapphideaway.foodfoundation.Services.RecipeService
import kotlinx.android.synthetic.main.fragment_result_list.view.*
import kotlinx.coroutines.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ResultFragment.OnListFragmentInteractionListener] interface.
 */
class ResultFragment : Fragment() {

    private var featuredAdapter: ResultAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_result_list, container, false)

        val bundle = arguments
        val recipeResponse = bundle!!.getSerializable("results") as RecipeResponse


            layoutManager = LinearLayoutManager(rootView.context)
            featuredAdapter = ResultAdapter(recipeResponse, rootView.context)

            rootView.search_recipe_recycler_view.adapter = featuredAdapter
            rootView.search_recipe_recycler_view.layoutManager = layoutManager


        return rootView
    }


}
