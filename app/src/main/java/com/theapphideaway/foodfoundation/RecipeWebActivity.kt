package com.theapphideaway.foodfoundation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class RecipeWebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_web)

        //getWindow().requestFeature(Window.FEATURE_PROGRESS);

        //val myWebView: WebView = findViewById(R.id.web_view)
//        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
//
//
//        myWebView.webViewClient = object : WebViewClient() {
//
//            override fun onPageFinished(view: WebView, url: String) {
//                val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
//                progressbar.visibility = View.GONE
//            }
//        }
//        try{
//            var bundle:Bundle=intent.extras
//            myWebView.loadUrl(bundle.getString("Url") )
//        }catch (ex:Exception){
//
//        }
    }
}
