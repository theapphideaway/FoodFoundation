package com.theapphideaway.foodfoundation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.theapphideaway.foodfoundation.Fragments.SavedRecipeFragment
import com.theapphideaway.foodfoundation.Fragments.SearchRecipeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_my_recipes -> {
                switchToSavedRecipes()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                switchToSearchRecipes()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun switchToSavedRecipes() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(SavedRecipeFragment())
            .replace(R.id.fragment, SavedRecipeFragment()).commit()

    }
    fun switchToSearchRecipes() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(SearchRecipeFragment())
            .replace(R.id.fragment, SearchRecipeFragment()).commit()
    }
}
