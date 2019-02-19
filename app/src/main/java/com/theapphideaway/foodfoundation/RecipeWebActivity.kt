package com.theapphideaway.foodfoundation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_recipe_web.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipeWebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_recipe_web)


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
                web_view.loadUrl(url)
            } catch (ex: Exception) {
                println("Oops")
            }

    }
}
