package com.theapphideaway.foodfoundation.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


import com.theapphideaway.foodfoundation.Models.RecipeResponse
import com.theapphideaway.foodfoundation.R
import com.theapphideaway.foodfoundation.RecipeWebActivity

import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultAdapter(
    private val mRecipeResponse: RecipeResponse,
    private val context: Context
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mRecipeResponse.results[position]
        holder.mTitle.text = item.title


        if(item.thumbnail != "") Picasso.get().load(item.thumbnail).into(holder.mThumbnail)

        holder.itemView.setOnClickListener { goToArticle(item.href) }

    }

    override fun getItemCount(): Int = mRecipeResponse.results.size

    fun goToArticle(url: String){
        var intent = Intent(context, RecipeWebActivity::class.java)
        intent.putExtra("Url", url)
        startActivity(context, intent, null)
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.title
        val mThumbnail: ImageView = mView.food_image


    }
}
